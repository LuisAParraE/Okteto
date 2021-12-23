package com.example.styleoverflow.styleoverflow.project.getProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project"])
class GetProjectController (
    private val getProjectService: GetProjectService
){

    @GetMapping(path = ["/get"])
    @ResponseBody
    fun getProject(@RequestBody getProjectRequest: GetProjectRequest): ResponseEntity<Any>{

        return getProjectService.getP(getProjectRequest)
    }

    @GetMapping(path = ["/get/user"])
    @ResponseBody
    fun getUserProjects(@RequestBody getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any>{

        return getProjectService.getUserP(getListOfProjectRequest)
    }

    @GetMapping(path = ["/get/all"])
    @ResponseBody
    fun getProjects(@RequestBody getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any>{

        return getProjectService.getListP(getListOfProjectRequest)
    }
}