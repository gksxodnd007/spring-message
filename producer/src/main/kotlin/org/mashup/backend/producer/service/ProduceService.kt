package org.mashup.backend.producer.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.mashup.backend.common.domain.constant.ChannelType
import org.mashup.backend.common.domain.dto.LedgerApolloDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private const val TOPIC = "voyager-apollo"

@Service
class ProduceService(private val kafkaTemplate: KafkaTemplate<String, String>, private val objectMapper: ObjectMapper) {

    fun produceSettlementTarget(payload: LedgerApolloDto, channelType: ChannelType) {
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(payload))
    }
}