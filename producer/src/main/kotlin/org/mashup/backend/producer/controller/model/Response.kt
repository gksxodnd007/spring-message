package org.mashup.backend.producer.controller.model

data class SimpleResponse(val message: String, val code: Int)

data class ResponseWithData<T>(val message: String, val code: Int, val data: T)