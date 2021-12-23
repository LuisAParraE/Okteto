package com.example.styleoverflow.styleoverflow.project.deleteProject

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class DeleteProjectRequest (
    val projectId: Long,
    val ownerId: Long,
    val sessionId : String
)

