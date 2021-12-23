package com.example.styleoverflow.styleoverflow.project.getProject

import com.example.styleoverflow.styleoverflow.project.Project
import org.springframework.data.domain.Page

/**
 * Class tha is the response for get a list
 */
class GetProjectListToken (
    val pagina: List<Project>,
    val more: Boolean
)