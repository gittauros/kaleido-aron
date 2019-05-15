package com.tauros.kaleido.aron.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @date 2019/5/14
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(characterEncodingFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("CharacterEncodingFilter");
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter());
//        converters.add(fastJsonHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(mappingJackson2XmlHttpMessageConverter());
    }

//    @Bean
//    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        supportedMediaTypes.add(MediaType.TEXT_HTML);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig());
//        return fastJsonHttpMessageConverter;
//    }
//
//    @Bean
//    public FastJsonConfig fastJsonConfig() {
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        SerializeConfig serializeConfig = new SerializeConfig();
//        serializeConfig.put(Long.class, ToStringSerializer.instance);
//        serializeConfig.put(Double.class, ToStringSerializer.instance);
//        serializeConfig.put(long.class, ToStringSerializer.instance);
//        serializeConfig.put(double.class, ToStringSerializer.instance);
//        fastJsonConfig.setSerializeConfig(serializeConfig);
//        return fastJsonConfig;
//    }

    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        return converter;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        return new StringHttpMessageConverter();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}


