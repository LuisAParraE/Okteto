package com.example.styleoverflow.styleoverflow.project.deleteProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.Project
import com.example.styleoverflow.styleoverflow.project.ProjectService
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Service Class that validates all the info for the deletion of a project
 */
@Service
@AllArgsConstructor
class DeleteProjectService (
    private val projectService: ProjectService,
    private val accessTokenService : AccessTokenService

){
    /**
    * Function that receives a message and validate all the information and deletes the project
    * @param deleteProjectRequest A message that contains all the information needed for the deletion of a project.
    * @return A response message if the deletion was successful or not.
    */
    fun deleteP(deleteProjectRequest: DeleteProjectRequest): ResponseEntity<Any>{

        //VALIDATIONS
        val isValid = accessTokenService.isValidAccessTokenByString(deleteProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        val ownerId :Long? = accessTokenService.getUserFromToken(deleteProjectRequest.sessionId)?.getId()

        ownerId?.let {
            return projectService.deleteProject(
                deleteProjectRequest.projectId,
                it
            )
        }

        return ResponseEntity("NULL USER ID FOUND", HttpStatus.INTERNAL_SERVER_ERROR)

    }
}