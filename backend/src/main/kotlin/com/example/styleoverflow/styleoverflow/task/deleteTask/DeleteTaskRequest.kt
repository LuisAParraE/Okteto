package com.example.styleoverflow.styleoverflow.task.deleteTask

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.ToString

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
data class DeleteTaskRequest (
    val taskId: Long,
    val sessionId: String
)