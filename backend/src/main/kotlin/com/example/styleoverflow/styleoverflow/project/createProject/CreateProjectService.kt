package com.example.styleoverflow.styleoverflow.project.createProject

import com.example.styleoverflow.styleoverflow.accessToken.AccessTokenService
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.project.Project
import com.example.styleoverflow.styleoverflow.project.ProjectService
import com.example.styleoverflow.styleoverflow.validators.ProjectDateValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectDescriptionValidator
import com.example.styleoverflow.styleoverflow.validators.ProjectNameValidator
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/**
 * Service Class that validates all the info for the creation of a project
 */
@Service
class CreateProjectService(
    private val projectService: ProjectService,
    private val projectNameValidator: ProjectNameValidator,
    private val projectDescriptionValidator: ProjectDescriptionValidator,
    private val projectDateValidator: ProjectDateValidator,
    private val accessTokenService: AccessTokenService
) {
    /**
     * Function that receives a message and validate all the information and creates the project
     * @param createProjectRequest A message that contains all the information needed for the creation of a project.
     * @return A response message if the creation was successful or not.
     */
    fun createP(createProjectRequest: CreateProjectRequest): ResponseEntity<Any> {

        //VALIDATIONS

        val isValidToken = accessTokenService.isValidAccessTokenByString(createProjectRequest.sessionId)

        if (!isValidToken)
            throw BRequestException("Invalid Token. Session Expired")

        if (!projectNameValidator.test(createProjectRequest.name))
            throw BRequestException("Nombre no Valido")

        if (!projectDescriptionValidator.test(createProjectRequest.description))
            throw BRequestException("Descripción no Valida")

        if (!projectDateValidator.test(createProjectRequest.beginDate, createProjectRequest.endDate))
            throw BRequestException("Fecha de Inicio o Fecha de Fin no Valida")

        return projectService.createProject(
            Project(
                createProjectRequest.name,
                createProjectRequest.description,
                createProjectRequest.beginDate,
                createProjectRequest.endDate,
                createProjectRequest.owner,
                true
            )
        )
    }
}
