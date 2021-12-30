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

/**
 * Service in charge of al the interactions of the AppUser Class
 */
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

    /**
     * Function in charge of validate a correct login and return the session token ID
     * @param loginRequest A message with all the information needed to do the login.
     * @return A message containing a session ID token for later requests calls or an error message if something failed
     */
    fun login(loginRequest: LoginRequest): ResponseEntity<Any> {
        val thisUser = appUserRepository.findByUsername(loginRequest.username)
        val userExists = thisUser.isPresent

        // validate user existence
        if (!userExists)
            throw BRequestException("No such user")

        val toMatchPassword = thisUser.get().password

        // validate passwords matching
        if (!bCryptPasswordEncoder.matches(loginRequest.password, toMatchPassword))
            throw BRequestException("Password Mismatch.")

        // generate access token
        val generatedSessionId: String = UUID.randomUUID().toString()

        val accessToken = AccessToken(
            sessionId = generatedSessionId,
            createdAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(15),
            appUser = thisUser.get()
        )

        // save access token
        accessTokenService.saveAccessToken(accessToken)

        // increase the number of user's active sessions
        thisUser.get().setSessions(thisUser.get().getSessions() + 1)
        appUserRepository.save(thisUser.get())

        // generate token to return
        val accessTokenMessage = AppUserAccessToken(
            message = "Successful login",
            session_id = generatedSessionId
        )

        // return access token over a http 200 response
        return ResponseEntity.ok(accessTokenMessage)
    }

    /**
     * Performs exactly logout. Assumes validity of token has been previously done
     * @param request A message with all the information needed to do the logout
     * @return A message that verifies a successful logout or an error message if something failed
     */
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

    /**
     * Creates a new AppUser Entry
     * @param appUser A User entity that means to be saved en DB
     * @return A message of success if everything went well or an error message if not
     */
    fun signUp(appUser: AppUser): ResponseEntity<Any> {
        val userExist = appUserRepository
            .findByUsername(appUser.username)
            .isPresent

        if (userExist)
            throw BRequestException("Username already taken.")

        // Encrypt the password
        val encodedPassword = bCryptPasswordEncoder.encode(appUser.password)

        // Save the new Encrypted Password
        appUser.password = encodedPassword

        // Save user on DB
        appUserRepository.save(appUser)

        // Create and send confirmation token
        val appUserValidationToken = AppUserValidationToken(
            message = "User Created Successfully",
            username = appUser.username,
            name = appUser.getName(),
            surname = appUser.getSurname(),
            birthdate = appUser.getBirthdate()
        )
        return ResponseEntity(appUserValidationToken, HttpStatus.OK)
    }

    companion object {
        private const val USER_NOT_FOUND_MESSAGE = "user %s not found"
    }
}
