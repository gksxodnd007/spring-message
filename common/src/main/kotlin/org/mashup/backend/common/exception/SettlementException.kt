package org.mashup.backend.common.exception

import java.lang.RuntimeException

class NotFoundPgFeeRule(val msg: String) : RuntimeException(msg)