package com.example.styleoverflow.styleoverflow.project.updateProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/update"])
class UpdateProjectController (
    private val updateProjectService: UpdateProjectService
){

    @PostMapping
    @ResponseBody
    fun updateProject(@RequestBody updateProjectRequest: UpdateProjectRequest): ResponseEntity<Any> {
        return updateProjectService.updateP(updateProjectRequest)
    }
}