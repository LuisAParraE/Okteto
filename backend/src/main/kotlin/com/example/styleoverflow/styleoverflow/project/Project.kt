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
    private var id: Long? = null

    fun getId(): Long?{
        return id
    }

}
