package com.example.styleoverflow.styleoverflow.project.deleteProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/delete"])
class DeleteProjectController(
    private val deleteProjectService: DeleteProjectService
) {

    @DeleteMapping
    @ResponseBody
    fun deleteProject(@RequestBody deleteProjectRequest: DeleteProjectRequest): ResponseEntity<Any> {
        return deleteProjectService.deleteP(deleteProjectRequest)
    }
}