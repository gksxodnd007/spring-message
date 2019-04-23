package org.mashup.backend.common.domain.repository

import org.mashup.backend.common.domain.constant.MessageStatus
import org.mashup.backend.common.domain.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long> {

    fun findByStatus(status: MessageStatus)
}