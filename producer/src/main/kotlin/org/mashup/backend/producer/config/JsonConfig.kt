package org.mashup.backend.producer.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class JsonConfig {

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder.json()
            .serializersByType(mapOf(LocalDate::class.java to LocalDateSerializer(), LocalDateTime::class.java to LocalDateTimeSerializer()))
            .deserializersByType(mapOf(LocalDate::class.java to LocalDateDeserializer(), LocalDateTime::class.java to LocalDateTimeDeserializer()))
            .modules(listOf(KotlinModule(), JavaTimeModule()))
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .build()
    }

    private inner class LocalDateSerializer : JsonSerializer<LocalDate>() {

        override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen!!.writeString(value!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        }
    }

    private inner class LocalDateDeserializer : JsonDeserializer<LocalDate>() {

        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate {
            return LocalDate.parse(p!!.valueAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
    }

    private inner class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {

        override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen!!.writeString(value!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        }
    }

    private inner class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {

        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
            return LocalDateTime.parse(p!!.valueAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }
    }
}
