package com.example.styleoverflow.styleoverflow.project

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDate
import javax.persistence.*

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "project")
/**
 * Represents a Project
 * @param name Represents the name of the project
 * @param projectDesc Represents the description(additional information) of a project
 * @param beginDate Is the beginning date of a project
 * @param endDate Is the ending date of a project
 * @param owner Is the ID of the User who created the project
 * @param projectStatus Depicts is the project is active or not (If is deleted or not)
 * @return A project Entity.
 */
class Project(
    var name: String,
    @Column(
        name = "description",
        columnDefinition = "TEXT"
    )
    var projectDesc: String,
    var beginDate: LocalDate,
    var endDate: LocalDate,
    var owner: Long,
    var projectStatus: Boolean
) {
    @SequenceGenerator(
        name = "project_sequence",
        sequenceName = "project_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "project_sequence"
    )
    /**
     * Is the ID generated for every project(UNIQUE)
     */
    private var id: Long? = null

    fun getId(): Long?{
        return id
    }

}
