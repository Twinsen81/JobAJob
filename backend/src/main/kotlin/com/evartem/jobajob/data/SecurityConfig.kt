package com.evartem.jobajob.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Suppress("unused")
@Configuration
@EnableResourceServer
class SecurityConfig : ResourceServerConfigurerAdapter() {
    @Value("\${security.oauth2.resource.id}")
    private val resourceId: String? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers(HttpMethod.GET, "/vacancies/**").permitAll()
                .antMatchers(HttpMethod.GET, "/resumes/**").permitAll()
                .mvcMatchers("/vacancies/**").authenticated()
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(resourceId)
    }
}