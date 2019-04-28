package org.mashup.backend.producer.controller

import org.mashup.backend.common.domain.constant.ChannelType
import org.mashup.backend.common.domain.dto.LedgerApolloDto

import org.mashup.backend.producer.controller.model.SimpleResponse
import org.mashup.backend.producer.exception.BadRequestException
import org.mashup.backend.producer.service.ProduceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1"])
class LedgerProduceApi(private val producerService: ProduceService) {

    @PostMapping(value = ["/ledger/produce/channelTypes/{channelType}"], consumes = ["application/json"])
    fun produceLedger(@PathVariable(name = "channelType") channelType: String,
                      @RequestBody payload: LedgerApolloDto
    ): SimpleResponse {
        try {
            producerService.produceSettlementTarget(payload, ChannelType.valueOf(channelType))
            return SimpleResponse("OK", 204)
        } catch (e: IllegalArgumentException) {
            throw BadRequestException("정산이 지원되지않는 채널입니다.", HttpStatus.BAD_REQUEST.value())
        }
    }
}