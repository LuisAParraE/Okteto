package com.example.styleoverflow.styleoverflow.registration

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api/v1/registration"])
@AllArgsConstructor
class RegistrationController(
    private val registrationService: RegistrationService
) {

    @PostMapping
    @CrossOrigin(origins = ["http://localhost:13300"])
    @ResponseBody
    fun register(@RequestBody request: RegistrationRequest): ResponseEntity<Any> {
        return registrationService.register(request)
    }
}
