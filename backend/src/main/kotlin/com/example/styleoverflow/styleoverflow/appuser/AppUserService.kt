package com.example.styleoverflow.styleoverflow.appuser

import com.example.styleoverflow.styleoverflow.accessToken.AccessToken
import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.login.LoginRequest
import com.example.styleoverflow.styleoverflow.logout.LogoutRequest
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.Throws

@Service
@AllArgsConstructor
class AppUserService(
    private val appUserRepository: AppUserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val accessTokenService: AccessTokenService,
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        return appUserRepository.findByUsername(username!!)
            .orElseThrow {
                UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MESSAGE, username)
                )
            }
    }

    fun login(loginRequest: LoginRequest): ResponseEntity<Any> {
        val thisUser = appUserRepository.findByUsername(loginRequest.username)
        val userExists = thisUser.isPresent

        // validate user existence
        if (!userExists)
            throw BRequestException("No such user")

        val toMatchPassword = thisUser.get().password

        // validate passwords matching
        if (!bCryptPasswordEncoder.matches(loginRequest.password, toMatchPassword))
            throw BRequestException("Password missmatch.")

        // generate access token
        val generated_session_id: String = UUID.randomUUID().toString()

        val accessToken = AccessToken(
            sessionId = generated_session_id,
            createdAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(15),
            appUser = thisUser.get()
        )

        // save access token
        accessTokenService.saveAccessToken(accessToken)

        // increase the number of user's active sessions
        thisUser.get().setSessions(thisUser.get().getSessions() + 1)
        appUserRepository.save(thisUser.get())

        println("${thisUser.get().getSessions()} y ${thisUser.get().getSessions() + 1}")

        // generate token to return
        val accessTokenMessage = AppUserAccessToken(
            message = "Successful login",
            session_id = generated_session_id
        )

        // return access token over a http 200 response
        return ResponseEntity.ok(accessTokenMessage)
    }

    // Performs exactly logout. Assumes validity of token has been previously done
    fun logout(request: LogoutRequest): ResponseEntity<Any> {

        val token = accessTokenService.getAccessToken(request.sessionId)

        if (token != null) {
            val tokenUser = token.appUser

            // update # of user active sessions
            tokenUser.setSessions(tokenUser.getSessions() - 1)

            appUserRepository.save(tokenUser)

            // deactivate Token
            accessTokenService.deactivateToken(token)
        }

        return ResponseEntity.ok("Successful logout")
    }

    fun signUp(appUser: AppUser): ResponseEntity<Any> {
        val userExist = appUserRepository
            .findByUsername(appUser.username)
            .isPresent

        check(!userExist) {
            "username already taken"
        }

        val encodedPassword = bCryptPasswordEncoder.encode(appUser.password)

        appUser.password = encodedPassword
        appUserRepository.save(appUser)

        // CREATE AND SEND CONFIRMATION TOKEN
        val appUserValidationToken = AppUserValidationToken(
            message = "User Created Succesfully",
            username = appUser.username,
            name = appUser.getName(),
            surname = appUser.getSurname(),
            birthdate = appUser.getBirthdate()
        )
        return ResponseEntity(appUserValidationToken, HttpStatus.OK) // elicits http response with 200 code, too basic
    }

    companion object {
        private const val USER_NOT_FOUND_MESSAGE = "user %s not found"
    }
}
