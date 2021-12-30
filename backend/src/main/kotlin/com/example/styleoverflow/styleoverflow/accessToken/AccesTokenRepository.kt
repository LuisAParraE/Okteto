package com.example.styleoverflow.styleoverflow.accessToken

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


/**
 * Interface for comunication with an accessToken table on a DB. It handles CRUD operations.
 */
@Repository
@Transactional(readOnly = true)
interface AccessTokenRepository : JpaRepository<AccessToken, Long> {

    /**
     * Performs lookup of an accessToken provided it's related sessionId.
     * @param session_id : id of the related session.
     * @return : maybe an existing token.
     */
    fun findBySessionId(session_id: String): AccessToken?
}