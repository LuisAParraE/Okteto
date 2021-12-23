package com.example.styleoverflow.styleoverflow.project.createProject

import java.time.LocalDate

data class ProjectCreationToken(
    val message: String?,
    val name: String,
    val beginDate: LocalDate,
    val endDate: LocalDate
)
