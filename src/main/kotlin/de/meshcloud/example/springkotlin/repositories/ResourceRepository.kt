package de.meshcloud.example.springkotlin.repositories

import de.meshcloud.example.springkotlin.model.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface ResourceRepository : JpaRepository<Resource, Long>
