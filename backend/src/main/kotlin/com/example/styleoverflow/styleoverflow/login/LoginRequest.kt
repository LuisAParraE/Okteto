package com.example.styleoverflow.styleoverflow.login

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class LoginRequest (
    val username : String,
    val password : String
)