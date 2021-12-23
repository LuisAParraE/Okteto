package com.example.styleoverflow.styleoverflow.project.createProject

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString
import java.time.LocalDate

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
