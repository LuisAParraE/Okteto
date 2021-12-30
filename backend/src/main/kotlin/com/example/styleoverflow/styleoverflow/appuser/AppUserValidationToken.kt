package com.example.styleoverflow.styleoverflow.appuser

import java.time.LocalDate

/**
 * Data class that works as response for a successful user creation
 * @param message A message which tells a user was created
 * @param username The username of the new user entry
 * @param name The name of the new user entry
 * @param surname The surname of the new user entry
 * @param birthdate The birthday of the new user entry
 */
data class AppUserValidationToken(
    val message: String?,
    val username: String,
    val name: String,
    val surname: String,
    val birthdate: LocalDate,
)