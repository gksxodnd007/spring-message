package org.mashup.backend.producer.exception

import java.lang.RuntimeException

class BadRequestException(val msg: String, val code: Int) : RuntimeException(msg)