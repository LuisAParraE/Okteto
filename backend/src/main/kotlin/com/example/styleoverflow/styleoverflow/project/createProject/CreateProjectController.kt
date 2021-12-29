package com.example.styleoverflow.styleoverflow.project.createProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/create"])
/**
 * Controller class that manages the creation project requests made to the endpoint.
 */
class CreateProjectController(
    private val createProjectService: CreateProjectService
) {
    /**
     * Function to create a project.
     * @param createProjectRequest A message with all the information needed to create a project.
     * @return A response message if the creation was successful or not.
     */
    @PostMapping
    @ResponseBody
    fun createProject(@RequestBody createProjectRequest: CreateProjectRequest): ResponseEntity<Any> {
        return createProjectService.createP(createProjectRequest)
    }
}
