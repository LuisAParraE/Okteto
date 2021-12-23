package com.example.styleoverflow.styleoverflow.login

import com.example.styleoverflow.styleoverflow.appuser.AppUserService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.validators.PasswordValidator
import com.example.styleoverflow.styleoverflow.validators.UserNameValidator
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class LoginService (
    private val userNameValidator : UserNameValidator,
    private val passwordValidator: PasswordValidator,
    private val appUserService: AppUserService
)
{
    fun login(request: LoginRequest): ResponseEntity<Any> {
        if (!userNameValidator.test(request.username)) {
            throw BRequestException("Invalid username.")
        }

        if (!passwordValidator.test(request.password)) {
            throw BRequestException("Invalid password. It must contain exactly 8 characters.")

        }
        return appUserService.login(request)
    }
}