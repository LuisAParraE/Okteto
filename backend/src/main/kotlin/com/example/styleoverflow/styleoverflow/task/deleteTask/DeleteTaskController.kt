package com.example.styleoverflow.styleoverflow.task.deleteTask

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@AllArgsConstructor
@RestController
@RequestMapping(path = ["api/v1/task/delete"])
class DeleteTaskController(
    private val deleteTaskService: DeleteTaskService
) {

    @DeleteMapping
    @ResponseBody
    fun deleteTask(@RequestBody deleteTaskRequest: DeleteTaskRequest): ResponseEntity<Any>{
        return deleteTaskService.deleteTask(deleteTaskRequest)
    }
}