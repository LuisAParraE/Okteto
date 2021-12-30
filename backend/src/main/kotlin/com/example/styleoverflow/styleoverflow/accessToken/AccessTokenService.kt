package com.example.styleoverflow.styleoverflow.accessToken

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Service to handle operations over access token
 * @param accessTokenRepository : Existent repository with a accesToken table where CRUD operations are actually carried
 * out.
 */
@Service
@AllArgsConstructor
class AccessTokenService(
    private val accessTokenRepository: AccessTokenRepository
) {
    /**
     * Retrieves an access token by sessionId.
     * @param sessionId : id of an existing session.
     * @return : the accesToken related to the given id.
     */
    fun getAccessToken(sessionId: String): AccessToken? {
        return accessTokenRepository.findBySessionId(sessionId)
    }

    /**
     * Saves the access token into the repository.
     * @param token : accesToken to be saved.
     */
    fun saveAccessToken(token: AccessToken) {
        accessTokenRepository.save(token)
    }

    /**
     * Checks whether an accessToken is valid using it's related sessionId.
     * @param sessionId : id of the session we want to validate with the token.
     * @return : a boolean representing whether the token is valid or not.
     */
    fun isValidAccessTokenByString(sessionId: String): Boolean {
        val accessToken = getAccessToken(sessionId)

        if (accessToken != null) {
            val isActive = accessToken.active
            val expired = accessToken.expiresAt.isBefore(LocalDateTime.now())

            return isActive && !expired
        }

        return false
    }

    /**
     * Used to invalidate an accessToken.
     * @param token : accessToken to invalidate.
     */
    fun deactivateToken(token: AccessToken) {
        token.active = false
        accessTokenRepository.save(token)
    }

    /**
     * Returns a user given a session Id related to an access token
     * @param sessionId : session id of the login session being queried.
     * @return : User id of the underlying user session.
     */
    fun getUserFromToken(sessionId : String) : AppUser? {
        return getAccessToken(sessionId)?.appUser;
    }
}