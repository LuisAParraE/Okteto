package com.example.styleoverflow.styleoverflow.task.deleteTask

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller class to manage requests to delete a task.
 * @param deleteTaskService : Delete service to reroute delete requests.
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = ["api/v1/task/delete"])
class DeleteTaskController(
    private val deleteTaskService: DeleteTaskService
) {

    /**
     * Function to delete a task from the controller point of view.
     * @param deleteTaskRequest : Request received with necessary fields to delete a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    @DeleteMapping
    @ResponseBody
    fun deleteTask(@RequestBody deleteTaskRequest: DeleteTaskRequest): ResponseEntity<Any>{
        return deleteTaskService.deleteTask(deleteTaskRequest)
    }
}