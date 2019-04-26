package org.mashup.backend.producer.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.mashup.backend.producer.controller.model.ShipmentTransactionDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private const val TOPIC = "shipment"

@Service
class ProduceService(private val kafkaTemplate: KafkaTemplate<out Any, out Any>, private val objectMapper: ObjectMapper) {

    fun produceMessage(payload: ShipmentTransactionDto) {
        (kafkaTemplate as KafkaTemplate<String, String>).send(TOPIC, objectMapper.writeValueAsString(payload))
    }
}