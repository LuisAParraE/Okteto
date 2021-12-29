package com.example.styleoverflow.styleoverflow.task.createTask

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/***
 * Token representing a create task request.
 * @param sessionId : Id of an existing session.
 * @param description : description of the task.
 * @param status : status of the task.
 * @param name : name of the task.
 * @param projectId : Id of the project the task belongs to.
 *
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class CreateTaskRequest(
    val sessionId: String,
    val description: String,
    val status: String,
    val name: String,
    val projectId: String,

)
