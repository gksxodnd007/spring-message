package org.mashup.backend.apigw.controller.model

data class ApiResponse<T>(val code: Int, val msg: String, val result: T)

data class SimpleApiResponse(val code: Int, val msg: String)