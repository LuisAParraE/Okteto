package com.example.styleoverflow.styleoverflow.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
interface TaskRepository : JpaRepository<TaskEntity, Long> {
    fun findTaskEntitiesByName(name: String): Optional<TaskEntity>
}
