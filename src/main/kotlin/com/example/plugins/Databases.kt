package com.example.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

var APP_DATA_SOURCE: HikariDataSource? = null
fun Application.configureDatabases() {
    val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:mysql://localhost:3306/jno_testapi"
        driverClassName = "org.mariadb.jdbc.Driver"
        username = "jno_api"
        password = "jno_api"
        maximumPoolSize = 5
    }
    val dataSource = HikariDataSource(hikariConfig)
    val database = Database.connect(dataSource)
    APP_DATA_SOURCE = dataSource

    // Datenbank Verbindungstest
    transaction(database) {
        val result = exec("SELECT 1") {
            it.next()
            it.getInt(1)
        }
        println("Datenbank funktioniert: ${result == 1}")
    }

}
