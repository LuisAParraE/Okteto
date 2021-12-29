package com.example.styleoverflow.styleoverflow.project.getProject

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller class that manages get project requests made to the endpoints.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/project"])
class GetProjectController (
    private val getProjectService: GetProjectService
){
    /**
     * Function to get all the information of a project.
     * @param getProjectRequest A message with all the information needed to get the info of a project.
     * @return A response message with all the info of the project or an error message.
     */
    @GetMapping(path = ["/get"])
    @ResponseBody
    fun getProject(@RequestBody getProjectRequest: GetProjectRequest): ResponseEntity<Any>{

        return getProjectService.getP(getProjectRequest)
    }

    /**
     * Function to get a list of a user's projects information separated on pages.
     * @param getListOfProjectRequest A message with all the information needed to get the list.
     * @return A response message with all the info of the projects or an error message.
     */
    @GetMapping(path = ["/get/user"])
    @ResponseBody
    fun getUserProjects(@RequestBody getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any>{

        return getProjectService.getUserP(getListOfProjectRequest)
    }

    /**
     * Function to get a list of projects that doesn't belong to a user, separated on pages.
     * @param getListOfProjectRequest A message with all the information needed to get the list.
     * @return A response message with all the info of the projects or an error message.
     */
    @GetMapping(path = ["/get/all"])
    @ResponseBody
    fun getProjects(@RequestBody getListOfProjectRequest: GetListOfProjectRequest): ResponseEntity<Any>{

        return getProjectService.getListP(getListOfProjectRequest)
    }
}