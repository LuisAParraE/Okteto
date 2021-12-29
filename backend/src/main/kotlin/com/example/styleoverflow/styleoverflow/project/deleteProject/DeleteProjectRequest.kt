package com.example.styleoverflow.styleoverflow.project.deleteProject

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/**
 * Data class that specify all the attributes needed for the Delete Project Controller
 * to accept the request
 * @param projectId ID of the project that wants to delete
 * @param ownerId ID of the user doing the deletion
 * @param sessionId Session token to check if the session is active
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class DeleteProjectRequest (
    val projectId: Long,
    val ownerId: Long,
    val sessionId : String
)

