package org.mashup.backend.common.domain.repository

import org.mashup.backend.common.domain.entity.SettlementBase
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementBaseRepository : JpaRepository<SettlementBase, Long> {
}