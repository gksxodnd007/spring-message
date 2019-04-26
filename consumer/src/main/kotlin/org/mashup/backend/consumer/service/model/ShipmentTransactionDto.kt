package org.mashup.backend.consumer.service.model

import java.time.LocalDateTime

data class ShipmentTransactionDto(
    val paymentAmount: Double? = null,
    val transactionId: String? = null,
    val externalTransactionId: String? = null,
    val transactedAt: LocalDateTime? = null
)