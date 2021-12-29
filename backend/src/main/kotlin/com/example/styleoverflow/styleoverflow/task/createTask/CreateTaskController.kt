package com.example.styleoverflow.styleoverflow.task.createTask

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/***
 * Controller class to manage request to create a task.
 * @param createTaskService : create task service to reroute create task request.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/task/create"])
class CreateTaskController(
    private val createTaskService: CreateTaskService
) {
    /***
     * Function to create a task from the controller point of view.
     * @param createTaskRequest : request received with necessary fields to create a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    @PostMapping
    @ResponseBody
    fun createTask(@RequestBody createTaskRequest: CreateTaskRequest): ResponseEntity<Any> {
        return createTaskService.createTask(createTaskRequest)
    }
}
