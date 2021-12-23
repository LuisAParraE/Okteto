package com.example.styleoverflow.styleoverflow.project.createProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project/create"])
class CreateProjectController(
    private val createProjectService: CreateProjectService
) {

    @PostMapping
    @ResponseBody
    fun createProject(@RequestBody createProjectRequest: CreateProjectRequest): ResponseEntity<Any> {
        return createProjectService.createP(createProjectRequest)
    }
}
