package com.example.styleoverflow.styleoverflow.registration

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString
import java.time.LocalDate
import java.util.*

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class RegistrationRequest(
    val name: String,
    val surname: String,
    val username: String,
    val email: String,
    val password: String,
    val birthdate: LocalDate,
)
