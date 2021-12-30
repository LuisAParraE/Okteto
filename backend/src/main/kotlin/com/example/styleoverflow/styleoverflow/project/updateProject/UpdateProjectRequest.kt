package com.example.styleoverflow.styleoverflow.project.updateProject

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/**
 * Data class that specify all the attributes needed for the Update Project Controller
 * to accept the request
 * @param projectId ID of the project that wants to update
 * @param name New name of the project(Or the same one if not have a modification)
 * @param description New description of the project(Or the same one if not have a modification)
 * @param sessionId Session token to check if the session is active
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class UpdateProjectRequest (
    val projectId: Long,
    val name: String,
    val description: String,
    val sessionId : String
)