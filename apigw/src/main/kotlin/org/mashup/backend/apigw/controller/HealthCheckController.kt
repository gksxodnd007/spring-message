package org.mashup.backend.apigw.controller

import org.mashup.backend.apigw.controller.model.SimpleApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/api/health-check")
    fun healthCheck(): SimpleApiResponse {
        return SimpleApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name)
    }
}