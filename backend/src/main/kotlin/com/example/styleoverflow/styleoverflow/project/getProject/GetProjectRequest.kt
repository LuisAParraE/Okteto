package com.example.styleoverflow.styleoverflow.project.getProject

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/**
 * Data class that specify all the attributes needed for the Get Project Controller
 * to accept the request
 * @param projectId ID of the project that wants to get
 * @param sessionId Session token to check if the session is active
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class GetProjectRequest (
    val projectId: Long,
    val sessionId : String
)