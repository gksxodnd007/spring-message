package org.mashup.backend.consumer.service

import org.mashup.backend.common.domain.constant.ChannelType
import org.mashup.backend.common.domain.dto.LedgerApolloDto
import org.mashup.backend.common.service.SettlementService
import org.springframework.stereotype.Service


@Service
class ConsumeService(private val settlementService: SettlementService ) {

    fun consumeQuattroTransaction(ledgerApolloDto: LedgerApolloDto) {
        settlementService.settleTransaction(ledgerApolloDto, ChannelType.QUATTRO)
    }
}