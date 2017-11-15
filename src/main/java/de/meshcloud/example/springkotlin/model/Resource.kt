package de.meshcloud.example.springkotlin.model

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
data class Resource (

  @Id
  @GeneratedValue
  val id: Long? = null,

  var name: String,

  var description: String? = null,

  var type: ResourceType,

  var startDate: LocalDateTime,

  var endDate: LocalDateTime,

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  var project: Project

) {
  // default constructor is required for JPA
  private constructor() : this (
      name = "",
      type = ResourceType.CLOUDFOUNDRY,
      startDate = LocalDateTime.now(),
      endDate = LocalDateTime.now(),
      project = Project()
  )

  fun durationInHours(): Long {
    return startDate.until(endDate, ChronoUnit.HOURS)
  }

}
