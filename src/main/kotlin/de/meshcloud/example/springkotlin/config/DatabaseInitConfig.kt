package de.meshcloud.example.springkotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DatabaseInitConfig(

    // prefer @Value over @ConfigurationProperties, because otherwise variables have to be "lateinit var"
    // and therefore mandatory config properties won't cause the application fail during start, but only throw
    // an exception during runtime
    @Value("\${app.projectName}")
    val projectName: String
)