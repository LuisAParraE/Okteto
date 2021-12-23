package com.example.styleoverflow.styleoverflow.accessToken

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Repository
@Transactional(readOnly = true)
interface AccessTokenRepository : JpaRepository<AccessToken, Long> {

    fun findBySessionId(session_id: String): AccessToken?

/*    @Transactional
    @Modifying
    @Query(
        "UPDATE AccessToken c " +
                "SET c.confirmedAt = ?2 " +
                "WHERE c.token = ?1"
    )

    fun updateConfirmedAt( token: String, confirmedAt: LocalDateTime ): Int*/
}