package org.mashup.backend.common.domain.constant

enum class PaymentStatus(val description: String) {
    CREATED("생성"),
    APPROVED("승인"),
    PARTIAL_CANCELED("부분 취소"),
    FULL_CANCELED("전체 취소"),
    PARTIAL_FULL_CANCELED("부분전체 취소"),
    VOID("미확인"),
    INCOMPLETE("미완");
}