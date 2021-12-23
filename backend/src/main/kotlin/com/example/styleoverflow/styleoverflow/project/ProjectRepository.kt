package com.example.styleoverflow.styleoverflow.project

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional(readOnly = true)
interface ProjectRepository : JpaRepository<Project, Long> {
    fun findProjectByName(name: String): Optional<Project>
    fun findProjectByOwnerAndProjectStatus(owner: Long, projectStatus: Boolean, pageable: Pageable): Optional<Page<Project>>
    fun findProjectByOwnerNotAndProjectStatus(owner: Long, projectStatus: Boolean, pageable: Pageable): Optional<Page<Project>>
}
