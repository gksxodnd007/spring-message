package org.mashup.backend.consumer.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val httpMessageConverter = MappingJackson2HttpMessageConverter()
        httpMessageConverter.objectMapper = objectMapper
        converters.add(httpMessageConverter)
    }
}