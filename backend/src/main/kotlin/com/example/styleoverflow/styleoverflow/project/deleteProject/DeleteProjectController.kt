package com.example.styleoverflow.styleoverflow.project.deleteProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller class that manages delete project requests made to the endpoint.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/delete"])
class DeleteProjectController(
    private val deleteProjectService: DeleteProjectService
) {
    /**
     * Function to delete a project.
     * @param deleteProjectRequest A message with all the information needed to delete a project.
     * @return A response message if the deletion was successful or not.
     */
    @PostMapping
    @ResponseBody
    fun deleteProject(@RequestBody deleteProjectRequest: DeleteProjectRequest): ResponseEntity<Any> {
        return deleteProjectService.deleteP(deleteProjectRequest)
    }
}