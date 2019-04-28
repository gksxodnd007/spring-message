package org.mashup.backend.producer.exception

import org.mashup.backend.producer.controller.model.SimpleResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun badRequestHandler(badRequestException: BadRequestException) : ResponseEntity<SimpleResponse> {
        return ResponseEntity(SimpleResponse(badRequestException.message ?: "잘못된 요청입니다.", badRequestException.code), HttpStatus.BAD_REQUEST)
    }
}