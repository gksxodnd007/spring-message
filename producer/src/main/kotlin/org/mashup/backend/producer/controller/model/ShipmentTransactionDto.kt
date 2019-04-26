package org.mashup.backend.producer.controller.model

import java.time.LocalDateTime

data class ShipmentTransactionDto(
    val paymentAmount: Double,
    val transactionId: String,
    val externalTransactionId: String,
    val transactedAt: LocalDateTime
)