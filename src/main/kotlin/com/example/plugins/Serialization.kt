package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*

// Erweiterungsfunktion für das Application-Objekt zur Konfiguration der Serialisierung.
fun Application.configureSerialization() {
    // Installiert das ContentNegotiation-Plugin in der Ktor-Anwendung.
    // ContentNegotiation ist ein wichtiges Plugin, das es der Anwendung ermöglicht,
    // eingehende und ausgehende Daten in verschiedenen Formaten (wie JSON, XML usw.) zu verarbeiten.
    install(ContentNegotiation) {
        // Konfiguriert die JSON-Serialisierung mit den Standardeinstellungen.
        // Dies ermöglicht es Ihrer Anwendung, automatisch Objekte von und zu JSON zu konvertieren,
        // wenn sie als HTTP-Anfragen empfangen oder als HTTP-Antworten gesendet werden.
        json()
    }
}
