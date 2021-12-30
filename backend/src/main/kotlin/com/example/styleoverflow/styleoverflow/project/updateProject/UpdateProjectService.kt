package com.example.styleoverflow.styleoverflow.project.updateProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.ProjectService
import com.example.styleoverflow.styleoverflow.validators.ProjectDateValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectDescriptionValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectNameValidator
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Service Class that validates all the info for the update of a project
 */
@Service
@AllArgsConstructor
class UpdateProjectService(
    private val projectService: ProjectService,
    private val projectNameValidator: ProjectNameValidator,
    private val projectDescriptionValidator: ProjectDescriptionValidator,
    private val accessTokenService : AccessTokenService
) {
    /**
     * Function that receives a message and validate all the information and updates the project
     * @param updateProjectRequest A message that contains all the information needed for the update of a project.
     * @return A response message if the update was successful or not.
     */
    fun updateP(updateProjectRequest: UpdateProjectRequest): ResponseEntity<Any>{

        //VALIDATIONS

        val isValid = accessTokenService.isValidAccessTokenByString(updateProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        if(!projectNameValidator.test(updateProjectRequest.name))
            throw BRequestException("Nombre no Valido")

        if(!projectDescriptionValidator.test(updateProjectRequest.description))
            throw BRequestException("Descripción no Valida")

        val ownerId :Long? = accessTokenService.getUserFromToken(updateProjectRequest.sessionId)?.getId()

        ownerId?.let {
            return projectService.updateProject(
                updateProjectRequest.projectId,
                updateProjectRequest.name,
                updateProjectRequest.description,
                it
            )
        }

        return ResponseEntity("NULL USER ID FOUND", HttpStatus.INTERNAL_SERVER_ERROR)
    }

}