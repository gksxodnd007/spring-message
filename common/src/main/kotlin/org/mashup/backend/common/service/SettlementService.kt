package org.mashup.backend.common.service

import org.mashup.backend.common.domain.constant.ChannelType
import org.mashup.backend.common.domain.constant.PaymentStatus
import org.mashup.backend.common.domain.dto.LedgerApolloDto
import org.mashup.backend.common.domain.entity.SettlementBase
import org.mashup.backend.common.domain.repository.PgFeeRuleRepository
import org.mashup.backend.common.domain.repository.SettlementBaseRepository
import org.mashup.backend.common.exception.NotFoundPgFeeRule
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SettlementService(private val settlementBaseRepository: SettlementBaseRepository, private val pgFeeRuleRepository: PgFeeRuleRepository) {

    fun settleTransaction(ledger: LedgerApolloDto, channelType: ChannelType) {
        if (ledger.paymentStatus == PaymentStatus.VOID.name) {
            TODO("망취소일 경우 필요한 로직")
        }

        val settlementBase = SettlementBase().apply {
            this.externalTransactionId = ledger.externalTransactionId
            this.postTransactionId = ledger.postTransactionId
            this.channelType = channelType
            this.cid = ledger.cid
            this.paymentAmount = ledger.paymentAmount

            this.applySettlement(
                ledger.paymentAmount ?: BigDecimal.ZERO,
                ledger.cancelAmount ?: BigDecimal.ZERO,
                pgFeeRuleRepository.findByCid(ledger.cid!!) ?: throw NotFoundPgFeeRule("해당 ${ledger.cid}에 대한 룰 정보가 없습니다.")
            )
        }

        settlementBaseRepository.save(settlementBase)
    }
}