package com.example.styleoverflow.styleoverflow.task

import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import com.example.styleoverflow.styleoverflow.task.createTask.CreateTaskResponse
import com.example.styleoverflow.styleoverflow.task.updateTask.UpdateTaskRequest
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

/***
 * Service that handles all the main logic for operations on tasks such as creation, deletion, update and queries.
 * @param taskRepository : Existent repository of task where CRUD operations are actually carried out.
 */
@Service
@AllArgsConstructor
class TaskService(
    private val taskRepository: TaskRepository
) {
    /***
     * Function to perform the main logic of task creation.
     * @param task: Task token with necessary information for task creation.
     * @return A confirmation message with relevant information about what happened.
     */
    fun createTask(task: TaskEntity): ResponseEntity<Any> {

        taskRepository.save(task)

        val createdTask = CreateTaskResponse(
            message = "Task succesfully created",
            id= task.getId(),
            name = task.name,
            projectId = task.projectID.toString()
        )
        return ResponseEntity(createdTask, HttpStatus.CREATED)
    }

    /***
     * Function to preform the main logic of task deletion. Checks the existence of the requested task to delete
     * and acts accordingly.
     * @param taskId : Id of the task to delete
     * @return : A confirmation message with relevant information about what happened.
     */
    fun deleteTask(taskId: Long): ResponseEntity<Any> {

        val checkExists = taskRepository.existsById(taskId)

        if (!checkExists)
            throw BRequestException("Task doesn't exists")

        val taskEntity: TaskEntity = taskRepository.findById(taskId).get()

        taskRepository.delete(taskEntity)

        val taskConfirmation = TaskValidationToken (
            taskId = taskId,
            message = "Task successfully deleted."
        )
        return ResponseEntity(taskConfirmation, HttpStatus.OK)
    }

    /***
     * Logic for updating a task. Checks the existence of the requested task to update and acts accordingly.
     * @param updateTaskRequest : Request received with necessary fields to update a task.
     * @return : A confirmation message with relevant information about what happened.
     */
    fun updateTask(updateTaskRequest: UpdateTaskRequest) : ResponseEntity<Any> {

        val taskEntityQuery = taskRepository.findById(updateTaskRequest.taskId)

        // validate task existence
        if (!taskEntityQuery.isPresent)
            throw BRequestException("Task doesn't exists")

        val taskEntity = taskEntityQuery.get()

        // update task
        taskEntity.name = updateTaskRequest.name
        taskEntity.status = updateTaskRequest.status
        taskEntity.taskDescription = updateTaskRequest.description

        taskRepository.save(taskEntity);

        // create confirmation token
        val taskConfirmation = TaskValidationToken(
            message = "Task successfully updated.",
            taskId = taskEntity.getId()
        )

        return ResponseEntity(taskConfirmation, HttpStatus.CREATED);
    }

    /***
     * Function to get all tasks related to a proyect.
     * @param projectId : Id of the project whose task are being requested.
     * @return : Either a list of task related to an existing project or an exception is raised.
     */
    fun getTaskList(projectId: Long): List<TaskEntity>{
        return taskRepository.findTaskEntitiesByProjectID(projectId).get()
    }
}
