package org.mashup.backend.common.domain.entity

import org.mashup.backend.common.domain.constant.MessageStatus
import javax.persistence.*

@Entity
@Table(name = "message")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "message")
    var message: String? = null,
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    var status: MessageStatus? = null
)