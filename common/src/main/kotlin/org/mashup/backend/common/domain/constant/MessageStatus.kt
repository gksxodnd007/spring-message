package org.mashup.backend.common.domain.constant

enum class MessageStatus(val description: String) {

    PROGRESS("진행중"),
    SUCCESS("전송 성공"),
    FAIL("전송 실패")
}