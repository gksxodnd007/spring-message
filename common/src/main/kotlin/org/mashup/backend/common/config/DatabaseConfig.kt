package org.mashup.backend.common.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.message.hikari")
@PropertySource(value = ["classpath:db/spring-message-\${spring.profiles.active}-db.properties"])
class HikariCPProperties {

    lateinit var jdbcUrl: String
    lateinit var driverClassName: String
    lateinit var username: String
    lateinit var password: String
    lateinit var connectionTimeout: String
    lateinit var idleTimeout: String
    lateinit var maxLifetime: String
    lateinit var connectionTestQuery: String
    lateinit var minimumIdle: String
    lateinit var maximumPoolSize: String
    lateinit var poolName: String
    lateinit var prepStmtCacheSize: String
    lateinit var prepStmtCacheSqlLimit: String
    lateinit var cachePrepStmts: String

    override fun toString(): String {
        return "HikariCPProperties(jdbcUrl='$jdbcUrl', driverClassName='$driverClassName', username='$username', password='$password', connectionTimeout='$connectionTimeout', idleTimeout='$idleTimeout', maxLifetime='$maxLifetime', connectionTestQuery='$connectionTestQuery', minimumIdle='$minimumIdle', maximumPoolSize='$maximumPoolSize', poolName='$poolName', prepStmtCacheSize='$prepStmtCacheSize', prepStmtCacheSqlLimit='$prepStmtCacheSqlLimit', cachePrepStmts='$cachePrepStmts')"
    }
}

@Configuration
class DatabaseConfig(val hikariCPProperties: HikariCPProperties) {

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactory = LocalContainerEntityManagerFactoryBean()
        entityManagerFactory.dataSource = datasource()
        entityManagerFactory.setPackagesToScan("org.mashup.backend.common.domain")

        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactory.jpaVendorAdapter = jpaVendorAdapter
        entityManagerFactory.setJpaProperties(jpaProperties())

        return entityManagerFactory
    }

    @Bean
    fun platformTransactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = entityManagerFactory

        return jpaTransactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun datasource(): DataSource {
        val hikari = HikariDataSource()
        hikari.jdbcUrl = hikariCPProperties.jdbcUrl
        hikari.driverClassName = hikariCPProperties.driverClassName
        hikari.username = hikariCPProperties.username
        hikari.password = hikariCPProperties.password
        hikari.connectionTimeout = hikariCPProperties.connectionTimeout.toLong()
        hikari.idleTimeout = hikariCPProperties.idleTimeout.toLong()
        hikari.maxLifetime = hikariCPProperties.maxLifetime.toLong()
        hikari.connectionTestQuery = hikariCPProperties.connectionTestQuery
        hikari.minimumIdle = hikariCPProperties.minimumIdle.toInt()
        hikari.maximumPoolSize = hikariCPProperties.maximumPoolSize.toInt()
        hikari.poolName = hikariCPProperties.poolName
        hikari.addDataSourceProperty("prepStmtCacheSize", hikariCPProperties.prepStmtCacheSize)
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", hikariCPProperties.prepStmtCacheSqlLimit)
        hikari.addDataSourceProperty("cachePrepStmts", hikariCPProperties.cachePrepStmts)

        println(hikariCPProperties.toString())

        return  hikari
    }

    private fun jpaProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect")
        properties.setProperty("hibernate.show_sql", "true")
        properties.setProperty("hibernate.format_sql", "true")

        return properties
    }
}