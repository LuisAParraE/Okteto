package com.example.styleoverflow.styleoverflow.task.createTask

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class CreateTaskRequest(
    val sessionId: String,
    val description: String,
    val status: String,
    val name: String,
    val projectId: String,

)
