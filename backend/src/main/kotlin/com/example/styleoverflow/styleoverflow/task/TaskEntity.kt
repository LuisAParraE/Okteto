package com.example.styleoverflow.styleoverflow.task

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "task")
class TaskEntity(
    var name: String,
    @Column(
        name = "description",
        columnDefinition = "TEXT"
    )
    var taskDescription: String,
    var projectID: Long,
    var status: TaskStatus

) {
    @SequenceGenerator(
        name = "task_sequence",
        sequenceName = "task_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "task_sequence"
    )
    private var id: Long? = null
}
