package org.mashup.backend.producer.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@ConfigurationProperties(prefix = "spring.message.kafka")
@PropertySource(value = ["classpath:/kafka-config/kafka-config-\${spring.profiles.active}.properties"])
class KafkaServerProperties {

    lateinit var bootstrapServers: String
}

@Configuration
@EnableConfigurationProperties(value = [KafkaServerProperties::class])
class KafkaProducerConfig {

    @Bean
    fun producerFactory(kafkaServerProperties: KafkaServerProperties): ProducerFactory<String, String> {
        val props = mutableMapOf<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaServerProperties.bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return DefaultKafkaProducerFactory<String, String>(props)
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate<String, String>(producerFactory)
    }
}