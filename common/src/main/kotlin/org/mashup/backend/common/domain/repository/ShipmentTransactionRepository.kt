package org.mashup.backend.common.domain.repository

import org.mashup.backend.common.domain.entity.ShipmentTransaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ShipmentTransactionRepository : JpaRepository<ShipmentTransaction, Long> {

    fun findByTransactedAtGreaterThanEqualAndTransactedAtLessThan(from: LocalDateTime, to: LocalDateTime)
    fun findByTransactionId(transactionId: String)
}