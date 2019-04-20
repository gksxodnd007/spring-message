package org.mashup.backend.common

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CommonApplication

fun main(args: Array<String>) {
    val application = SpringApplication()
    application.webApplicationType = WebApplicationType.NONE
    application.run(CommonApplication::javaClass.name, *args)
}