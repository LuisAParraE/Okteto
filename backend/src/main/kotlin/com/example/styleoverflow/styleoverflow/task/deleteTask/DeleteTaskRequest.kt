package com.example.styleoverflow.styleoverflow.task.deleteTask

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/***
 * Token representing a task update request.
 * @param sessionId : Id of an existing session.
 * @param taskId : Id of the task to be deleted.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class DeleteTaskRequest (
    val taskId: Long,
    val sessionId: String
)