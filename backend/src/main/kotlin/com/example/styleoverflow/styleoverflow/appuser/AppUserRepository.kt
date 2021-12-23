package com.example.styleoverflow.styleoverflow.appuser

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
interface AppUserRepository : JpaRepository<AppUser, Long> {
    fun findByUsername(username: String): Optional<AppUser>
}
