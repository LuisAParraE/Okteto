package com.example.styleoverflow.styleoverflow.logout

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.appuser.AppUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LogoutService (
    private val appUserService: AppUserService,
    private val accessTokenService: AccessTokenService
)
{
    fun logout(request : LogoutRequest) : ResponseEntity<Any>{
        val isValidToken = accessTokenService.isValidAccessTokenByString(request.sessionId)

        if ( isValidToken )
            return appUserService.logout(request)

        return ResponseEntity("Invalid Token. Session Expired", HttpStatus.BAD_REQUEST)
    }
}