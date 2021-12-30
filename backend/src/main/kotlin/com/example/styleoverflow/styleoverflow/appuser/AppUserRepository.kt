package com.example.styleoverflow.styleoverflow.appuser

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Interface that interacts with the DB. It gets, saves and updates the information about Users.
 */
@Repository
@Transactional(readOnly = true)
interface AppUserRepository : JpaRepository<AppUser, Long> {

    /**
     * Gets the information of a user by its Username.
     * @param username Username you want to search on DB
     * @return An AppUser entity if exist, null if not.
     */
    fun findByUsername(username: String): Optional<AppUser>
}
