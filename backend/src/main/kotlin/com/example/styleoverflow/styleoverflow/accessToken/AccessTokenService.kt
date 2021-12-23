package com.example.styleoverflow.styleoverflow.accessToken

import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@AllArgsConstructor
class AccessTokenService(
    private val accessTokenRepository: AccessTokenRepository
) {
    fun getAccessToken(sessionId: String): AccessToken? {
        return accessTokenRepository.findBySessionId(sessionId)
    }

    fun saveAccessToken(token: AccessToken) {
        accessTokenRepository.save(token)
    }

    fun isValidAccessTokenByString(sessionId: String): Boolean {
        val accessToken = getAccessToken(sessionId)

        if (accessToken != null) {
            val isActive = accessToken.active // ### perhaphs unnecesary
            val expired = accessToken.expiresAt.isBefore(LocalDateTime.now())

            return isActive && !expired
        }

        return false
    }

    fun deactivateToken(token: AccessToken) {
        token.active = false
        accessTokenRepository.save(token)
    }
}
