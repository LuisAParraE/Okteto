package com.example.styleoverflow.styleoverflow.project.getProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.ProjectService
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class GetProjectService(
    private val projectService: ProjectService,
    private val accessTokenService : AccessTokenService
) {

    fun getUserP(getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getListOfProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        return projectService.getUserProjectList(
            getListOfProjectRequest.ownerId,
            getListOfProjectRequest.page
        )

    }

    fun getP(getProjectRequest: GetProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        return projectService.getUserProject(
            getProjectRequest.projectId
        )
    }

    fun getListP(getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getListOfProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        return projectService.getProjectList(
            getListOfProjectRequest.ownerId,
            getListOfProjectRequest.page
        )
    }
}