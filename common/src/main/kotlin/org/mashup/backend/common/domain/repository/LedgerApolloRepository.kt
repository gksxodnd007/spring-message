package org.mashup.backend.common.domain.repository

import org.mashup.backend.common.domain.entity.LedgerApollo
import org.springframework.data.jpa.repository.JpaRepository

interface LedgerApolloRepository : JpaRepository<LedgerApollo, Long> {
}