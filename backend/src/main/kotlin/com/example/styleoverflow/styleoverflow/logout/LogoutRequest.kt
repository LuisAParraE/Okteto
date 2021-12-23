package com.example.styleoverflow.styleoverflow.logout

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class LogoutRequest (
    val sessionId : String
)