package com.example.styleoverflow.styleoverflow.project.updateProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller class that manages update project requests made to the endpoint.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/update"])
class UpdateProjectController (
    private val updateProjectService: UpdateProjectService
){
    /**
     * Function to update a project.
     * @param updateProjectRequest A message with all the information needed to update a project.
     * @return A response message if the update was successful or not.
     */
    @PostMapping
    @ResponseBody
    fun updateProject(@RequestBody updateProjectRequest: UpdateProjectRequest): ResponseEntity<Any> {
        return updateProjectService.updateP(updateProjectRequest)
    }
}