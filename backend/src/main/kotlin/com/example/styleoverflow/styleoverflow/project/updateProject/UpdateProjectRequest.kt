package com.example.styleoverflow.styleoverflow.project.updateProject

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class UpdateProjectRequest (
    val projectId: Long,
    val name: String,
    val description: String,
    val ownerId: Long,
    val sessionId : String
)