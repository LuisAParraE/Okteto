package com.example.styleoverflow.styleoverflow.project.getProject

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

/**
 * Data class that specify all the attributes needed for the Get Project Controller
 * to accept the request
 * @param ownerId ID of the project's owner
 * @param page Which page wants to get
 * @param sessionId Session token to check if the session is active
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class GetListOfProjectRequest (
    val ownerId: Long,
    val page: Int,
    val sessionId : String
)