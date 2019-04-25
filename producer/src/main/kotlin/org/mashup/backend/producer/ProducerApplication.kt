package org.mashup.backend.producer

import org.mashup.backend.common.CommonApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [ProducerApplication::class, CommonApplication::class])
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}