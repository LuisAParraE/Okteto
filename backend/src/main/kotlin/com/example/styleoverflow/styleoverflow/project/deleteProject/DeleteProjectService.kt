package com.example.styleoverflow.styleoverflow.project.deleteProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.ProjectService
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class DeleteProjectService (
    private val projectService: ProjectService,
    private val accessTokenService : AccessTokenService

){
    fun deleteP(deleteProjectRequest: DeleteProjectRequest): ResponseEntity<Any>{

        val isValid = accessTokenService.isValidAccessTokenByString(deleteProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        return projectService.deleteProject(
            deleteProjectRequest.projectId,
            deleteProjectRequest.ownerId
        )
    }
}