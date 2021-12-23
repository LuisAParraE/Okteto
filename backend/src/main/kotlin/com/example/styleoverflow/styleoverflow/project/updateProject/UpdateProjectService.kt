package com.example.styleoverflow.styleoverflow.project.updateProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.ProjectService
import com.example.styleoverflow.styleoverflow.validators.ProjectDateValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectDescriptionValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectNameValidator
import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
@AllArgsConstructor
class UpdateProjectService(
    private val projectService: ProjectService,
    private val projectNameValidator: ProjectNameValidator,
    private val projectDescriptionValidator: ProjectDescriptionValidator,
    private val accessTokenService : AccessTokenService
) {

    fun updateP(updateProjectRequest: UpdateProjectRequest): ResponseEntity<Any>{

        val isValid = accessTokenService.isValidAccessTokenByString(updateProjectRequest.sessionId)

        if (!isValid)
            throw BRequestException("Invalid Token. Session Expired")

        if(!projectNameValidator.test(updateProjectRequest.name))
            throw BRequestException("Nombre no Valido")

        if(!projectDescriptionValidator.test(updateProjectRequest.description))
            throw BRequestException("Descripción no Valida")

        return projectService.updateProject(
            updateProjectRequest.projectId,
            updateProjectRequest.name,
            updateProjectRequest.description,
            updateProjectRequest.ownerId
        )
    }

}