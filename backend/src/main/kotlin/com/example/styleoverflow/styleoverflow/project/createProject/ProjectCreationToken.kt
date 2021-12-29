package com.example.styleoverflow.styleoverflow.project.createProject

import java.time.LocalDate

/**
 * Message send as response for a creation of a Project
 * @param message Message informing what happened
 * @param projectId ID of the project created
 * @param name Name of the project
 * @param beginDate Beginning date of the project
 * @param endDate Ending date of the project
 */
data class ProjectCreationToken(
    val message: String?,
    val projectId: Long?,
    val name: String,
    val beginDate: LocalDate,
    val endDate: LocalDate
)
