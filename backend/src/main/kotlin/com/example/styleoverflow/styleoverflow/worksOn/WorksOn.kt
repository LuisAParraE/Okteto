package com.example.styleoverflow.styleoverflow.worksOn

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import com.example.styleoverflow.styleoverflow.project.Project
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.ManyToOne
import kotlin.reflect.KClass

@Entity
@IdClass(WorksOnId::class)
class WorksOn(
    @Id
    @ManyToOne
    private val appUser: AppUser,
    @Id
    @ManyToOne
    private val project: Project
) {
}