package com.example.styleoverflow.styleoverflow.project.getProject

import com.example.styleoverflow.styleoverflow.project.Project
import org.springframework.data.domain.Page

/**
 * Data Class that is the response for the getting a list of projects
 * @param pagina List of projects of a certain page
 * @param more A boolean which tells if exits more pages
 */
class GetProjectListToken (
    val pagina: List<Project>,
    val more: Boolean
)