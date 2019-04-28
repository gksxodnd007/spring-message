package org.mashup.backend.common.domain.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "pg_fee_rule")
class PgFeeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "cid")
    var cid: String? = null

    @Column(name = "fee_rate", precision = 5, scale = 3)
    var feeRate: BigDecimal? = null
}