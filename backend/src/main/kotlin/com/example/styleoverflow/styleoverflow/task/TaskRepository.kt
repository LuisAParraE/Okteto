package com.example.styleoverflow.styleoverflow.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

/***
 * Interface for comunicating with a task table on a DB. It handles CRUD operations.
 */
@Repository
@Transactional(readOnly = true)
interface TaskRepository : JpaRepository<TaskEntity, Long> {
    /***
     * Finds all tasks related to a project ID.
     * @param projectID : Id of the project the task are related to.
     */
    fun findTaskEntitiesByProjectID(projectID: Long): Optional<List<TaskEntity>>
}
