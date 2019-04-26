package org.mashup.backend.producer.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(jackson2HttpMessageConverter())
    }

    private fun jackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = CustomObjectMapper()

        return converter
    }
}

class CustomObjectMapper : ObjectMapper() {

    init {
        val simpleModule = SimpleModule()
        simpleModule.addSerializer(LocalDateTime::class.java, LocalDateTimeJsonSerializer())
        simpleModule.addDeserializer(LocalDateTime::class.java, LocalDateTimeJsonDeserializer())

        registerModule(simpleModule)
    }
}

class LocalDateTimeJsonSerializer() : JsonSerializer<LocalDateTime>() {

    override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss").format(value))
    }
}

class LocalDateTimeJsonDeserializer() : JsonDeserializer<LocalDateTime>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        return LocalDateTime.parse(p?.text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss"))
    }
}
