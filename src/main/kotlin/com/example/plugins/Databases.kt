package com.example.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

// Globale Variable, um die Datenquelle (DataSource) zu speichern, die von HikariCP verwaltet wird.
var APP_DATA_SOURCE: HikariDataSource? = null

// Erweiterungsfunktion für das Application-Objekt zur Konfiguration der Datenbankverbindungen.
fun Application.configureDatabases() {
    // Erstellt eine HikariCP-Konfiguration.
    val hikariConfig = HikariConfig().apply {
        // JDBC-URL für die Datenbankverbindung.
        // Hier wird eine Verbindung zu einer MySQL-Datenbank auf localhost hergestellt.
        jdbcUrl = "jdbc:mysql://localhost:3306/jno_testapi"

        // Der JDBC-Treiber, hier für MariaDB.
        driverClassName = "org.mariadb.jdbc.Driver"

        // Benutzername und Passwort für die Datenbankverbindung.
        username = "jno_api"
        password = "jno_api"

        // Maximale Anzahl an Verbindungen im Pool.
        maximumPoolSize = 5
    }

    // Erstellt eine Datenquelle (DataSource) basierend auf der HikariCP-Konfiguration.
    val dataSource = HikariDataSource(hikariConfig)

    // Stellt eine Verbindung zur Datenbank über Exposed her, unter Verwendung der Hikari DataSource.
    val database = Database.connect(dataSource)

    // Speichert die Datenquelle in der globalen Variable, um sie später im Programm zu verwenden.
    APP_DATA_SOURCE = dataSource

    // Führt eine Transaktion mit der Datenbank aus, um die Verbindung zu testen.
    transaction(database) {
        // Führt eine einfache SQL-Abfrage ("SELECT 1") aus und überprüft das Ergebnis.
        val result = exec("SELECT 1") {
            it.next()
            it.getInt(1)
        }
        // Gibt auf der Konsole aus, ob die Datenbankverbindung funktioniert.
        println("Datenbank funktioniert: ${result == 1}")
    }
}
