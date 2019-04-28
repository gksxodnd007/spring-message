package org.mashup.backend.common.domain.entity

import org.mashup.backend.common.domain.constant.ChannelType
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "settlement_base")
class SettlementBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "origin_transaction_id")
    var externalTransactionId: String? = null

    @Column(name = "post_transaction_id")
    var postTransactionId: String? = null

    @Column(name = "cid")
    var cid: String? = null

    @Column(name = "channel_type")
    var channelType: ChannelType? = null

    @Column(name = "payment_amount")
    var paymentAmount: BigDecimal? = null

    @Column(name = "cancel_amount")
    var cancelAmount: BigDecimal? = null

    @Column(name = "payable_amount")
    var payableAmount: BigDecimal? = null

    @Column(name = "fee")
    var fee: BigDecimal? = null

    @Column(name = "fee_vat")
    var feeVat: BigDecimal? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pg_fee_rule_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var pgFeeRule: PgFeeRule? = null

    @Column(name = "target_date")
    var targetDate: LocalDate? = null

    @Column(name = "transacted_at")
    var transactedAt: LocalDateTime? = null

    fun applySettlement(paymentAmount: BigDecimal, cancelAmount: BigDecimal, pgFeeRule: PgFeeRule) {
        this.payableAmount = paymentAmount
        this.cancelAmount = cancelAmount
        this.targetDate = LocalDate.now().plusDays(2)
        this.pgFeeRule = pgFeeRule
        this.fee = this.payableAmount!! * pgFeeRule.feeRate!!
        this.feeVat = this.fee!! * BigDecimal.valueOf(0.1)
    }
}