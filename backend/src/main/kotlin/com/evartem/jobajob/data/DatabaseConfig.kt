package com.evartem.jobajob.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException
import javax.sql.DataSource

/*@Configuration
class DatabaseConfig {

    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        return HikariDataSource(config)
    }
}*/

/*
@Configuration
class DatabaseConfig {
    @Value("\${spring.datasource.url}")
    private var dbUrl: String? = null

    @Bean
    @Throws(SQLException::class)
    fun dataSource(): DataSource {

        if (dbUrl?.isEmpty() ?: true) {
            return HikariDataSource()
        } else {
            val config = HikariConfig()
            config.jdbcUrl = dbUrl
            return HikariDataSource(config)
        }
    }
}
*/

/*
@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(name: String) =
            "Hello again3, $name!"
}*/
