package org.mashup.backend.common.domain.entity

import org.mashup.backend.common.domain.constant.PaymentStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ledger_apollo")
class LedgerApollo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "origin_transaction_id")
    var externalTransactionId: String? = null

    @Column(name = "post_transaction_id")
    var postTransactionId: String? = null

    @Column(name = "cid")
    var cid: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    var paymentStatus: PaymentStatus? = null

    @Column(name = "payment_amount")
    var paymentAmount: BigDecimal? = null

    @Column(name = "cancel_amount")
    var cancelAmount: BigDecimal? = null

    @Column(name = "transacted_at")
    var transactedAt: LocalDateTime? = null
}