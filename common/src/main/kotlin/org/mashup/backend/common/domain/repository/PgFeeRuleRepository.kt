package org.mashup.backend.common.domain.repository

import org.mashup.backend.common.domain.entity.PgFeeRule
import org.springframework.data.jpa.repository.JpaRepository

interface PgFeeRuleRepository : JpaRepository<PgFeeRule, Long> {

    fun findByCid(cid: String): PgFeeRule?
}