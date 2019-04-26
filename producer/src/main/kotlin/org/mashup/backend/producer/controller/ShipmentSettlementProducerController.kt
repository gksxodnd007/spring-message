package org.mashup.backend.producer.controller

import org.mashup.backend.producer.controller.model.ShipmentTransactionDto
import org.mashup.backend.producer.service.ProduceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/interstellar/produce")
class ShipmentSettlementProducerController(val produceService: ProduceService) {

    @PostMapping(value = ["/shipmentTransaction"], consumes = ["application/json"])
    fun produceShipmentTransactionLedger(@RequestBody payload: ShipmentTransactionDto) {
        produceService.produceMessage(payload)
    }
}