package org.mashup.backend.common.domain.constant

import java.lang.RuntimeException

enum class FeePayoutType(val description: String) {

    ADVANCE("선취"),
    DEFER("후취");

    fun isPayable(): Boolean = this == ADVANCE

    companion object {
        private val POOL: Map<String, FeePayoutType> = FeePayoutType.values().map { it.description to it }.toMap()
        @JvmStatic fun findBy(description: String?): FeePayoutType = POOL[description] ?: throw RuntimeException(
            description
        )
    }
}