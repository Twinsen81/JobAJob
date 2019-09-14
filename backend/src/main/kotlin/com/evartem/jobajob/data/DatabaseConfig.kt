package com.evartem.jobajob.data

import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import java.net.URISyntaxException


@Configuration
class MainConfig {

    @Bean
    @Throws(URISyntaxException::class)
    fun dataSource(): BasicDataSource {
        val dbUri = URI(System.getenv("DATABASE_URL"))

        val username = dbUri.getUserInfo().split(":")[0]
        val password = dbUri.getUserInfo().split(":")[1]
        val dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require"

        val basicDataSource = BasicDataSource()
        basicDataSource.setUrl(dbUrl)
        basicDataSource.setUsername(username)
        basicDataSource.setPassword(password)

        return basicDataSource
    }
}

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
