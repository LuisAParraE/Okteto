package com.example.styleoverflow.styleoverflow.project

import com.example.styleoverflow.styleoverflow.appuser.AppUserRepository
import com.example.styleoverflow.styleoverflow.project.createProject.ProjectCreationToken
import com.example.styleoverflow.styleoverflow.project.getProject.GetProjectListToken
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
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val appUserRepository: AppUserRepository
) {
    /**
     * Function to create a Project, it verifies if the name already exist in DB
     * if not, it saves the project and returns a Verification Token
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
            message = "Project Succesfully Created",
            name = project.name,
            beginDate = project.beginDate,
            endDate = project.endDate
        )

        return ResponseEntity(projectCreationToken, HttpStatus.CREATED)
    }

    /**
     * This Method change the Name of a project and/or the description, it first validates
     * if another project has that name, after that check the owner and lastly it makes the
     * changes, save it and send the validation token
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
     *This method is in charge of changing the status of a Project From True to False
     * is Soft deleting the project.
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
     * Function to get all the information about a specific project by its Id.
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

        return ResponseEntity(
            projectRepository.findById(projectId).get(),
            HttpStatus.OK
        )

    }

    /**
     * Function to get a list of projects that by its Owner parameter. It's divided on pages
     * for less consume on resources by doing smaller requests.
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
     * Function to get a list of projects that doesn't belong to a user by and owner.
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
