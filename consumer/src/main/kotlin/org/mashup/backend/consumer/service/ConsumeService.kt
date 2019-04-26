package org.mashup.backend.consumer.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private const val TOPIC = "shipment"

@Service
class ConsumeService {

    @KafkaListener(topics = [TOPIC], groupId = "shipment")
    fun consumeMessage(message: String) {
        println(message)
    }
}