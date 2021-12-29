package com.example.styleoverflow.styleoverflow.project

/**
 * A class that only store information to send it as response
 * @param projectId ID of the project
 * @param message Message send as response
 */
class ProjectValidationToken(
    val projectId: Long,
    val message: String?

)
