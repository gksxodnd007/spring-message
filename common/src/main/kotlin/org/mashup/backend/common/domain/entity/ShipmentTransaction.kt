package org.mashup.backend.common.domain.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "shipment_transaction")
data class ShipmentTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "external_transaction_id")
    var externalTransactionId: String?,
    @Column(name = "transaction_id")
    var transactionId: String?,
    @Column(name = "payment_amount")
    var paymentAmount: Double?,
    @Column(name = "transacted_at")
    var transactedAt: LocalDateTime?
)