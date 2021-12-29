package com.example.styleoverflow.styleoverflow.task.deleteTask

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Class representing the deletion of a task as a service.
 * @param taskService : Main service of tasks where main logic of task deletion is carried out.
 * @param accessTokenService : Service for validation of the session token.
 *
 */
@Service
class DeleteTaskService(
    private val taskService: TaskService,
    private val accessTokenService: AccessTokenService
) {

    /**
     * Function to delete a task from the service point of view. Performs session token validation.
     * @param deleteTaskRequest : Request received with necessary fields to delete a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    fun deleteTask(deleteTaskRequest: DeleteTaskRequest): ResponseEntity<Any>{

        val isValidToken = accessTokenService.isValidAccessTokenByString(deleteTaskRequest.sessionId)

        if (!isValidToken)
            throw BRequestException("Invalid Token. Session Expired")

        return taskService.deleteTask( deleteTaskRequest.taskId )
    }

}