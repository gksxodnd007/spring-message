package org.mashup.backend.common.config

import com.zaxxer.hikari.HikariConfig
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
        entityManagerFactory.afterPropertiesSet()

        return entityManagerFactory
    }

    @Bean
    fun springMessageTransactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = entityManagerFactory

        return jpaTransactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun datasource(): DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = hikariCPProperties.jdbcUrl
        hikariConfig.driverClassName = hikariCPProperties.driverClassName
        hikariConfig.username = hikariCPProperties.username
        hikariConfig.password = hikariCPProperties.password
        hikariConfig.connectionTimeout = hikariCPProperties.connectionTimeout.toLong()
        hikariConfig.idleTimeout = hikariCPProperties.idleTimeout.toLong()
        hikariConfig.maxLifetime = hikariCPProperties.maxLifetime.toLong()
        hikariConfig.connectionTestQuery = hikariCPProperties.connectionTestQuery
        hikariConfig.minimumIdle = hikariCPProperties.minimumIdle.toInt()
        hikariConfig.maximumPoolSize = hikariCPProperties.maximumPoolSize.toInt()
        hikariConfig.poolName = hikariCPProperties.poolName
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", hikariCPProperties.prepStmtCacheSize)
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", hikariCPProperties.prepStmtCacheSqlLimit)
        hikariConfig.addDataSourceProperty("cachePrepStmts", hikariCPProperties.cachePrepStmts)

        return  HikariDataSource(hikariConfig)
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