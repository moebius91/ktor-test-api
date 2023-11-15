package com.example.plugins

import com.example.routes.recipeRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

// Eine Erweiterungsfunktion für die Klasse Application.
// Diese Funktion wird verwendet, um das Routing (die URL-Pfade und zugehörigen Aktionen) für die Anwendung zu konfigurieren.
fun Application.configureRouting() {
    // Der routing-Block definiert die Routen (URL-Pfade) für die Anwendung.
    routing {
        // Bindet die im recipeRoutes definierten Routen in die Anwendung ein.
        // Hier wird eine Funktion aufgerufen, die in einem anderen Teil des Projekts definiert ist,
        // um spezifische Routen für Rezepte (z.B. /rezepte) zu konfigurieren.
        recipeRoutes()
    }
}