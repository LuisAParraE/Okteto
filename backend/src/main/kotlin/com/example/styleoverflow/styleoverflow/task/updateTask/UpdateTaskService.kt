package com.example.styleoverflow.styleoverflow.task.updateTask

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.TaskService
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/***
 * Class representing the update of a task as a service.
 * @param taskService : Main service of tasks where main logic of task update is carried out.
 * @param accessTokenService : Service for validation of the session token.
 */
@Service
@AllArgsConstructor
class UpdateTaskService (
    private val taskService: TaskService,
    private val accessTokenService: AccessTokenService
){
    /***
     * Function to update a task from the service point of view. Performs session token validation.
     * @param updateTaskRequest : Request received with necessary fields to update a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    fun updateTask(updateTaskRequest : UpdateTaskRequest) : ResponseEntity<Any> {
        val isValidToken = accessTokenService.isValidAccessTokenByString(updateTaskRequest.sessionId)

        if (!isValidToken) throw BRequestException("Invalid Token, Session Expired")

        return taskService.updateTask( updateTaskRequest );
    }
}