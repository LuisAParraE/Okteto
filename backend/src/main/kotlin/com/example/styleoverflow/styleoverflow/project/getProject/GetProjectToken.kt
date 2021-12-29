package com.example.styleoverflow.styleoverflow.project.getProject

import com.example.styleoverflow.styleoverflow.project.Project
import com.example.styleoverflow.styleoverflow.task.TaskEntity
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/**
 * Data Class that is the response for the getting all the information of a project
 * @param project A project entity with all its info
 * @param tasks All the tasks of the project
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class GetProjectToken (
    val project: Project,
    val tasks: List<TaskEntity>
)