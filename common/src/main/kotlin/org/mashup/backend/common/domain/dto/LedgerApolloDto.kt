package org.mashup.backend.common.domain.dto

import java.math.BigDecimal

data class LedgerApolloDto(
    val externalTransactionId: String? = null,
    val postTransactionId: String? = null,
    val cid: String? = null,
    val paymentStatus: String? = null,
    val paymentAmount: BigDecimal? = null,
    val cancelAmount: BigDecimal? = null
)