package org.mashup.backend.consumer.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.mashup.backend.common.domain.dto.LedgerApolloDto
import org.mashup.backend.consumer.service.ConsumeService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private const val TOPIC = "voyager-apollo"

@Component
class KafkaMessageListener(private val consumeService: ConsumeService) {

    @KafkaListener(topics = [TOPIC], groupId = "apollo")
    fun consumeTransaction(message: String) {
        println(message)
        val objectMapper = ObjectMapper()
        val ledger = objectMapper.readValue<LedgerApolloDto>(message, LedgerApolloDto::class.java)

        consumeService.consumeQuattroTransaction(ledger)
    }
}