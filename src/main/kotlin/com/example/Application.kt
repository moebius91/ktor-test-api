// Definiert das Paket, in dem sich diese Datei befindet.
// Dies hilft bei der Organisation des Codes und vermeidet Konflikte mit Klassen gleichen Namens in anderen Paketen.
package com.example

// Importiert benötigte Funktionen und Klassen aus dem com.example.plugins-Paket
// und dem io.ktor.server.application-Paket.
import com.example.plugins.*
import io.ktor.server.application.*

// Die Hauptfunktion des Programms. Diese Funktion ist der Einstiegspunkt für Ihre Ktor-Serveranwendung.
fun main(args: Array<String>) {
    // Startet den Ktor-Server mit Netty als Engine.
    // Netty ist eine asynchrone Netzwerk-Programmierbibliothek, die häufig mit Ktor verwendet wird.
    io.ktor.server.netty.EngineMain.main(args)
}

// Eine Erweiterungsfunktion für das Application-Objekt.
// Diese Funktion wird aufgerufen, um verschiedene Konfigurationen für die Ktor-Anwendung festzulegen.
fun Application.module() {
    // Konfiguriert die Serialisierung für die Übertragung von Daten, z.B. beim Senden und Empfangen von JSON.
    configureSerialization()

    // Konfiguriert die Datenbankverbindung und -interaktionen.
    configureDatabases()

    // Konfiguriert Sicherheitsaspekte der Anwendung, wie Authentifizierung und Autorisierung.
    configureSecurity()

    // Konfiguriert die Routing-Mechanismen, um Anfragen an die entsprechenden Controller weiterzuleiten.
    configureRouting()
}
