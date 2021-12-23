package com.example.styleoverflow.styleoverflow.task.createTask

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.TaskEntity
import com.example.styleoverflow.styleoverflow.task.TaskService
import com.example.styleoverflow.styleoverflow.task.TaskStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CreateTaskService(
    private val taskService: TaskService,
    private val accessTokenService: AccessTokenService
) {
    fun createTask(createTaskRequest: CreateTaskRequest): ResponseEntity<Any> {
        val isValidToken = accessTokenService.isValidAccessTokenByString(createTaskRequest.sessionId)

        if (!isValidToken) throw BRequestException("Invalid Token, Sesion Expired")

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
