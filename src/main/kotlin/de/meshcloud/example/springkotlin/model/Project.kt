package de.meshcloud.example.springkotlin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Project(

    @Id
    @GeneratedValue
    val id: Long? = null,

    var name: String,

    var description: String? = null,

    @OneToMany(mappedBy = "project")
    val resources: MutableCollection<Resource> = mutableListOf()

)
