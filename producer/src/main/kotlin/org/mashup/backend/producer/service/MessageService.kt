package org.mashup.backend.producer.service

import org.mashup.backend.common.domain.constant.MessageStatus
import org.mashup.backend.common.domain.entity.Message
import org.mashup.backend.common.domain.repository.MessageRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MessageService(private val messageRepository: MessageRepository) {

    @Transactional
    fun sendMessage(message: String) {
        val entity = Message().apply {
            this.message = message
            this.status = MessageStatus.PROGRESS
        }

        messageRepository.save(entity)
    }
}