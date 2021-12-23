package com.example.styleoverflow.styleoverflow.project.getProject

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class GetListOfProjectRequest (
    val ownerId: Long,
    val page: Int,
    val sessionId : String
)