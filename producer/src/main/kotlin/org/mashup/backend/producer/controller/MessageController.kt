package org.mashup.backend.producer.controller

import org.mashup.backend.producer.controller.model.MessageDto
import org.mashup.backend.producer.service.MessageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(private val messageService: MessageService) {

    @PostMapping(path = ["/api/message"])
    fun doSendMessage(@RequestBody message: MessageDto) {
        messageService.sendMessage(message.message)
    }
}