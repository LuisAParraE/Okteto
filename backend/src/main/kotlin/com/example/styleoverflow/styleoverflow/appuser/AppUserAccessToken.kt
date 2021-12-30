package com.example.styleoverflow.styleoverflow.appuser

/**
 * Data class which is a response to a successful login.
 * @param message Message send to inform a successful login
 * @param session_id Session token ID for making validations on later requests
 */
data class AppUserAccessToken (
    val message : String,
    val session_id : String
)