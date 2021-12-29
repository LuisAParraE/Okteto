package com.example.styleoverflow.styleoverflow.task.createTask

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.TaskEntity
import com.example.styleoverflow.styleoverflow.task.TaskService
import com.example.styleoverflow.styleoverflow.task.TaskStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/***
 * Class representing the creation of a task as a service.
 * @param taskService : Main service of tasks where main logic of task update is carried out.
 * @param accessTokenService : Service for validation of the session token.
 */
@Service
class CreateTaskService(
    private val taskService: TaskService,
    private val accessTokenService: AccessTokenService
) {
    /***
     * Function to create a task form the service point of view.
     * @param createTaskRequest : Request received with necessary fields to create a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    fun createTask(createTaskRequest: CreateTaskRequest): ResponseEntity<Any> {
        val isValidToken = accessTokenService.isValidAccessTokenByString(createTaskRequest.sessionId)

        if (!isValidToken) throw BRequestException("Invalid Token, session Expired")

        return taskService.createTask(
            TaskEntity(
                createTaskRequest.name,
                createTaskRequest.description,
                createTaskRequest.projectId.toLong(),
                TaskStatus.TODO
            )
        )
    }
}
