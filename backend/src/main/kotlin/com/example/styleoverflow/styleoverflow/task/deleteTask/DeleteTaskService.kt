package com.example.styleoverflow.styleoverflow.task.deleteTask

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DeleteTaskService(
    private val taskService: TaskService,
    private val accessTokenService: AccessTokenService
) {

    fun deleteTask(deleteTaskRequest: DeleteTaskRequest): ResponseEntity<Any>{

        val isValidToken = accessTokenService.isValidAccessTokenByString(deleteTaskRequest.sessionId)

        if (!isValidToken)
            throw BRequestException("Invalid Token. Session Expired")

        return taskService.deleteTask(
            deleteTaskRequest.taskId
        )
    }

}