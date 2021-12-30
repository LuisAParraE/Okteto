package com.example.styleoverflow.styleoverflow.project.getProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.ProjectService
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Service Class that validates all the info for getting a project's info.
 */
@Service
@AllArgsConstructor
class GetProjectService(
    private val projectService: ProjectService,
    private val accessTokenService : AccessTokenService
) {

    /**
     * Function to get a list of a user's projects information separated on pages.
     * @param getListOfProjectRequest A message with all the information needed to get the list.
     * @return A response message with all the info of the projects or an error message.
     */
    fun getUserP(getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getListOfProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        val ownerId :Long? = accessTokenService.getUserFromToken(getListOfProjectRequest.sessionId)?.getId()

        ownerId?.let {
            return projectService.getUserProjectList(
                it,
                getListOfProjectRequest.page
            )
        }

        return ResponseEntity("NULL USER ID FOUND", HttpStatus.INTERNAL_SERVER_ERROR)

    }

    /**
     * Function to get all the information of a project.
     * @param getProjectRequest A message with all the information needed to get the info of a project.
     * @return A response message with all the info of the project or an error message.
     */
    fun getP(getProjectRequest: GetProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        return projectService.getUserProject(
            getProjectRequest.projectId
        )
    }

    /**
     * Function to get a list of projects that doesn't belong to a user, separated on pages.
     * @param getListOfProjectRequest A message with all the information needed to get the list.
     * @return A response message with all the info of the projects or an error message.
     */
    fun getListP(getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any> {

        //Session Token Validation
        val isValid = accessTokenService.isValidAccessTokenByString(getListOfProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        val ownerId :Long? = accessTokenService.getUserFromToken(getListOfProjectRequest.sessionId)?.getId()

        ownerId?.let {
            return projectService.getProjectList(
                it,
                getListOfProjectRequest.page
            )
        }

        return ResponseEntity("NULL USER ID FOUND", HttpStatus.INTERNAL_SERVER_ERROR)


    }
}