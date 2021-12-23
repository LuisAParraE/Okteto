package com.example.styleoverflow.styleoverflow.registration

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import com.example.styleoverflow.styleoverflow.appuser.AppUserRole
import com.example.styleoverflow.styleoverflow.appuser.AppUserService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.validators.*
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
@AllArgsConstructor
class RegistrationService(
    private val namingValidator : NamingValidation,
    private val userNameValidator : UserNameValidator,
    private val emailValidator: EmailValidator,
    private val appUserService: AppUserService,
    private val passwordValidator: PasswordValidator,
    private val dateValidator : DateValidator
) {

    fun register(request: RegistrationRequest): ResponseEntity<Any> {

        if(!namingValidator.test(request.name))
            throw BRequestException("Invalid Name.")

        if (!namingValidator.test(request.surname))
            throw BRequestException("Invalid Surname.")

        if (!userNameValidator.test(request.username) )
            throw BRequestException("Invalid Username.")

/*
        if (!emailValidator.test(request.email))
            throw BRequestException("Provided Email is not well formatted")
*/
        if(!dateValidator.test(request.birthdate))
            throw BRequestException("Invalid birthdate.")

        if (!passwordValidator.test(request.password))
            throw BRequestException("Invalid Password. It must consist exactly 8 characters.")


        return appUserService.signUp(
            AppUser(
                request.name,
                request.surname,
                request.username,
                request.email,
                request.password,
                request.birthdate,
                AppUserRole.USER
            )
        )
    }
}
