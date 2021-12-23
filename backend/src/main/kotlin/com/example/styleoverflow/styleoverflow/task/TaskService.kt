package com.example.styleoverflow.styleoverflow.task

import com.example.styleoverflow.styleoverflow.task.createTask.CreateTaskResponse
import com.example.styleoverflow.styleoverflow.task.deleteTask.DeleteTaskResponse
import lombok.AllArgsConstructor
import org.hibernate.annotations.Check
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun createTask(task: TaskEntity): ResponseEntity<Any> {

        taskRepository.save(task)

        val createdTask = CreateTaskResponse(
            message = "Task succesfully created",
            name = task.name,
            projectId = task.projectID.toString()
        )
        return ResponseEntity(createdTask, HttpStatus.CREATED)
    }

    fun deleteTask(taskId: Long): ResponseEntity<Any> {

        check(taskRepository.existsById(taskId)){
            "Task Doesn't exist"
        }

        val taskEntity: TaskEntity = taskRepository.findById(taskId).get()

        taskRepository.delete(taskEntity)

        return ResponseEntity("Task Successfully Deleted", HttpStatus.OK)
    }
}
