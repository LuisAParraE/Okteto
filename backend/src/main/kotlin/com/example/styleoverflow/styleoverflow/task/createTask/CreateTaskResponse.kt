package com.example.styleoverflow.styleoverflow.task.createTask

/***
 * Token with relevant information representing a task was created successfully.
 * @param message : Message containing a description of what happened.
 * @param id : Id of the task that was created.
 * @param name : name of the task thta was created.
 * @param projectId : Id of the project the task belongs to.
 */
data class CreateTaskResponse(
    val message: String?,
    val id: Long?,
    val name: String?,
    val projectId: String?,
)
