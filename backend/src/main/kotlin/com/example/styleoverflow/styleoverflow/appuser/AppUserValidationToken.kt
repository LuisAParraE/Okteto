package com.example.styleoverflow.styleoverflow.appuser

import java.time.LocalDate

class AppUserValidationToken(
    val message: String?,
    val username: String,
    val name: String,
    val surname: String,
    val birthdate: LocalDate,
)