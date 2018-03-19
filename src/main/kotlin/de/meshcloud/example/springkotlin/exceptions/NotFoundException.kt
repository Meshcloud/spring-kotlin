package de.meshcloud.example.springkotlin.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested entity does not exist!")
class NotFoundException(message: String) : RuntimeException(message)
