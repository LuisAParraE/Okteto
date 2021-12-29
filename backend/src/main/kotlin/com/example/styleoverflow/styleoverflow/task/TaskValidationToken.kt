package com.example.styleoverflow.styleoverflow.task

/***
 * Generic token to confirm that an action over a task has been successfully performed.
 * @param taskId : Id of the task the action was successfully performed.
 * @param message : Message containing a description of what happened.
 */
data class TaskValidationToken(
    val taskId: Long?,
    val message: String
)
