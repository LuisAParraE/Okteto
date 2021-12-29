package com.example.styleoverflow.styleoverflow.task.updateTask

import com.example.styleoverflow.styleoverflow.task.TaskStatus

/***
 * Token representing a task update request.
 * @param sessionId : Id of an existing session.
 * @param taskId : Id of the task to be updated
 * @param name : new name of the task.
 * @param description : new description of the task.
 * @param status : new status of the task.
 */
data class UpdateTaskRequest (
    val sessionId: String,
    val taskId : Long,
    val name: String,
    val description: String,
    val status: TaskStatus
)
