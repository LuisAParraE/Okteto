package com.example.styleoverflow.styleoverflow.project

import com.example.styleoverflow.styleoverflow.appuser.AppUserRepository
import com.example.styleoverflow.styleoverflow.project.createProject.ProjectCreationToken
import com.example.styleoverflow.styleoverflow.project.getProject.GetProjectListToken
import com.example.styleoverflow.styleoverflow.project.getProject.GetProjectToken
import com.example.styleoverflow.styleoverflow.task.TaskService
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

//Quantity of projects for page
const val pageSize: Int = 3

@Service
@AllArgsConstructor
/**
 * Service in charge of all the interactions of the Project Class
 */
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val appUserRepository: AppUserRepository,
    private val taskService: TaskService
) {
    /**
     * Function to create a Project.
     * @param project Entity project that wants to be saved on DB
     * @return A response message that specifies if the creation was successful or not.
     */
    fun createProject(project: Project): ResponseEntity<Any> {
        val projectExist = projectRepository
            .findProjectByName(project.name)
            .isPresent

        // Check if the Name already exist
        check(!projectExist) {
            "Project Name Already Taken"
        }

        // Save th project on DB
        projectRepository.save(project)

        val projectCreationToken = ProjectCreationToken(
            message = "Project Successfully Created",
            projectId = project.getId(),
            name = project.name,
            beginDate = project.beginDate,
            endDate = project.endDate
        )

        return ResponseEntity(projectCreationToken, HttpStatus.CREATED)
    }

    /**
     * Changes some attributes of project(name or description)
     * @param projectId The ID of the project which wants to modify
     * @param description The new description you want to add
     * @param ownerId ID of the user who wants to do the modification
     * @return A response message that specifies if the modification was successful or not.
     */
    fun updateProject(projectId: Long, name: String, description: String, ownerId: Long): ResponseEntity<Any> {

        // Check if the project you want to change the name Exists
        check(projectRepository.existsById(projectId)) {
            "Project Doesn't Exist"
        }

        val projectNameExist = projectRepository
            .findProjectByName(name)
            .isPresent

        // Check if the Name is already on use
        check(!projectNameExist or
                (projectRepository.findById(projectId).get().name.equals(name))) {
            "Project Name Already Taken"
        }

        val project: Project = projectRepository.findById(projectId).get()

        project.name = name
        project.projectDesc = description

        projectRepository.save(project)

        val projectValidationToken = ProjectValidationToken(
            projectId = projectId,
            message = "Project Successfully Updated",
        )

        return ResponseEntity(projectValidationToken, HttpStatus.OK)
    }

    /**
     * Changes the status of a project from active to inactive
     * @param projectId The ID of the project which wants to delete
     * @param ownerId ID of the user who wants to delete
     * @return A response message that specifies if the elimination was successful or not.
     */
    fun deleteProject(projectId: Long, ownerId: Long): ResponseEntity<Any> {

        // Check if the project you want to change the name Exists
        check(projectRepository.existsById(projectId)) {
            "Project Doesn't Exist"
        }

        val project: Project = projectRepository.findById(projectId).get()

/*        if (proyecto.owner == ownerId)
            proyecto.projectStatus = false
        else
            return ResponseEntity("Not Owner",HttpStatus.BAD_REQUEST)
 */
        project.projectStatus = false
        projectRepository.save(project)

        val projectValidationToken = ProjectValidationToken(
            projectId = projectId,
            message = "Project Successfully Deleted",
        )

        return ResponseEntity(projectValidationToken, HttpStatus.OK)
    }

    /**
     * Function to get all the information about a specific project by its ID
     * @param projectId The ID of the project
     * @return A message with all the information about the project or an error message if it fails.
     */
    fun getUserProject(projectId: Long): ResponseEntity<Any> {

        val projectExist = projectRepository
            .findById(projectId)
            .isPresent

        //Check if the project Exist
        check(projectExist) {
            "Project Doesn't Exist"
        }

        //Check if the project is Deleted
        check(
            projectRepository
                .findById(projectId)
                .get()
                .projectStatus
        ) {
            "Project Doesn't Exist"
        }

        val getProjectToken = GetProjectToken(
            project = projectRepository.findById(projectId).get(),
            tasks = taskService.getTaskList(projectId)
        )

        return ResponseEntity(
            getProjectToken,
            HttpStatus.OK
        )

    }

    /**
     * Function for finding a list of projects by its owner. It's divided on pages
     * @param ownerId The ID of the owner of the projects
     * @param page Which page of the PAGE type you want
     * @return A message with all the information if is found or an error message if it fails
     */
    fun getUserProjectList(ownerId: Long, page: Int): ResponseEntity<Any> {

        val appUserExist = appUserRepository
            .findById(ownerId)
            .isPresent

        //Check if the project Exist
        check(appUserExist) {
            "User Doesn't Exist"
        }

        val pageable: Pageable = PageRequest.of(page, pageSize)

        val pagina: Page<Project> = projectRepository
            .findProjectByOwnerAndProjectStatus(ownerId, true, pageable)
            .get()

        val projectListToken = GetProjectListToken(
            pagina.content,
            pagina.hasNext()
        )

        return ResponseEntity(projectListToken,HttpStatus.OK)
    }

    /**
     * Function for finding a list of projects that doesn't belong to an owner. It's divided on pages
     * @param ownerId The ID of the owner of the projects
     * @param page Which page of the PAGE type you want
     * @return A message with all the information if is found or an error message if it fails
     */
    fun getProjectList(ownerId: Long, page: Int): ResponseEntity<Any> {

        val appUserExist = appUserRepository
            .findById(ownerId)
            .isPresent

        //Check if the project Exist
        check(appUserExist) {
            "User Doesn't Exist"
        }

        //Set the limits of the page
        val pageable: Pageable = PageRequest.of(page, pageSize)

        //Get the page
        val pagina: Page<Project> = projectRepository
            .findProjectByOwnerNotAndProjectStatus(ownerId, true, pageable)
            .get()

        val projectListToken = GetProjectListToken(
            pagina.content,
            pagina.hasNext()
        )

        return ResponseEntity(projectListToken,HttpStatus.OK)
    }
}
