package com.example.styleoverflow.styleoverflow.task.createTask

import lombok.AllArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@AllArgsConstructor
@RequestMapping(path = ["api/v1/task/create"])
class CreateTaskController(
    private val createTaskService: CreateTaskService
) {
    @PostMapping
    @ResponseBody
    fun createTask(@RequestBody createTaskRequest: CreateTaskRequest): ResponseEntity<Any> {
        return createTaskService.createTask(createTaskRequest)
    }
}
