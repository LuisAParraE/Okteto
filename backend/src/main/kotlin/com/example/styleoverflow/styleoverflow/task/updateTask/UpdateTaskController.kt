package com.example.styleoverflow.styleoverflow.task.updateTask

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/***
 * Controller class to manage requests to update a task.
 * @param updateTaskService : Update service to reroute update requests.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/task/update"])
class UpdateTaskController (
    private val updateTaskService: UpdateTaskService
) {
    /***
     * Function to update a task from the controller point of view.
     * @param updateTaskRequest : Request received with necessary fields to update a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    @PostMapping
    @ResponseBody
    fun updateTask(@RequestBody updateTaskRequest: UpdateTaskRequest): ResponseEntity<Any> {
        return updateTaskService.updateTask(updateTaskRequest);
    }
}