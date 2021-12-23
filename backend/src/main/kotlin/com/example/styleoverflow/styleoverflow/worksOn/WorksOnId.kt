package com.example.styleoverflow.styleoverflow.worksOn

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import com.example.styleoverflow.styleoverflow.project.Project
import java.io.Serializable
import javax.persistence.Id

class WorksOnId (
    private val appUser: AppUser,
    private val project: Project
): Serializable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorksOnId

        if (appUser != other.appUser) return false
        if (project != other.project) return false

        return true
    }

    override fun hashCode(): Int {
        var result = appUser.hashCode()
        result = 31 * result + project.hashCode()
        return result
    }
}