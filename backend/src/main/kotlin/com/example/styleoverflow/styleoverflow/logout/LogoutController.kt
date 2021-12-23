package com.example.styleoverflow.styleoverflow.logout

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(path = ["api/v1/logout"])
@AllArgsConstructor
class LogoutController (
    val logoutService: LogoutService
){
    @PostMapping
    @ResponseBody
    fun logout(@RequestBody request: LogoutRequest): ResponseEntity<Any> {
        return logoutService.logout(request)
    }
}