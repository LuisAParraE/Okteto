package com.example.styleoverflow.styleoverflow.project

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
/**
 * Interface that interacts with the DB. It gets, deletes, saves and updates the information about Projects.
 */
interface ProjectRepository : JpaRepository<Project, Long> {
    /**
     * Find a project on DB for its name.
     * @param name Name of the project which wants to retrieve from DB
     * @return The project entity if exist or null if it doesn't.
     */
    fun findProjectByName(name: String): Optional<Project>

    /**
     * Find a list of projects by its owner and return it in a page format
     * @param owner The ID of the user.
     * @param projectStatus If the project is Active or not
     * @param pageable Which size of list and page you want to retrieve
     * @return A page entity which includes: A list of projects, and if exits more projects to retrieve.
     */
    fun findProjectByOwnerAndProjectStatus(owner: Long, projectStatus: Boolean, pageable: Pageable): Optional<Page<Project>>

    /**
     * Find a list of projects that doesn't belong to an owner and return it in a page format
     * @param owner The ID of the user.
     * @param projectStatus If the project is Active or not
     * @param pageable Which size of list and page you want to retrieve
     * @return A page entity which includes: A list of projects, and if exits more projects to retrieve.
     */
    fun findProjectByOwnerNotAndProjectStatus(owner: Long, projectStatus: Boolean, pageable: Pageable): Optional<Page<Project>>
}
