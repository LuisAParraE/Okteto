package com.example.styleoverflow.styleoverflow.project.createProject

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString
import java.time.LocalDate

/**
 * Data class that specify all the attributes needed for the Creation project Controller
 * to accept the request
 * @param name Name of the new project.
 * @param description Additional information of a project.
 * @param beginDate The beginning date of a project.
 * @param endDate The ending date of a project.
 * @param owner The user who creates the project.
 * @param sessionId The session token to check if the session is active.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class CreateProjectRequest(
    val name: String,
    val description: String,
    val beginDate: LocalDate,
    val endDate: LocalDate,
    val owner: Long,
    val sessionId: String
)
