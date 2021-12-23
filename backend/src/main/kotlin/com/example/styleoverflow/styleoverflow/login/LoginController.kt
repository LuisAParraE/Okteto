package com.example.styleoverflow.styleoverflow.login

import com.example.styleoverflow.styleoverflow.registration.RegistrationRequest
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/login"])
@AllArgsConstructor
class LoginController (
    private val loginService: LoginService
)
{
    @PostMapping
    @ResponseBody
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        return loginService.login(request)
    }
}