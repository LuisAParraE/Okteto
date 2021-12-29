package com.example.styleoverflow.styleoverflow.task

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.*

/***
 * Class representing a task.
 * @param name: Name of the task.
 * @param taskDescription: Description of the task.
 * @param projectID: id of the project the task belongs to.
 * @param status: Status of the task.
 */
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
    // Unique id generated for every task.
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

    fun getId(): Long?{
        return id
    }
}
