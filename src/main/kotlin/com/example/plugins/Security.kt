package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

// Erweiterungsfunktion für das Application-Objekt zur Konfiguration der Sicherheitsfunktionen.
fun Application.configureSecurity() {
    // JWT-Konfigurationsparameter.
    // In einer realen Anwendung sollten diese Werte aus einer Konfigurationsdatei oder Umgebungsvariablen gelesen werden.
    val jwtAudience = "jwt-audience"
    val jwtDomain = "https://jwt-provider-domain/"
    val jwtRealm = "ktor sample app"
    val jwtSecret = "secret"

    // Konfiguriert das Authentifizierungsmodul von Ktor.
    authentication {
        // Konfiguriert JWT-Authentifizierung.
        jwt {
            // Definiert den 'realm', der in Authentifizierungs- und Autorisierungsprozessen verwendet wird.
            realm = jwtRealm

            // Erstellt und konfiguriert einen JWT-Verifier.
            // Der Verifier ist für die Überprüfung der Integrität und Gültigkeit der JWTs zuständig.
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret)) // Verwendet HMAC256 Algorithmus mit dem geheimen Schlüssel.
                    .withAudience(jwtAudience)            // Stellt sicher, dass der JWT für die angegebene Audience ausgestellt wurde.
                    .withIssuer(jwtDomain)                // Überprüft den Aussteller (Issuer) des Tokens.
                    .build()
            )

            // Definiert die Logik zur Validierung des JWT-Credentials.
            // Diese Funktion bestimmt, ob das übergebene JWT gültig ist und einen authentifizierten Benutzer repräsentiert.
            validate { credential ->
                // Überprüft, ob die Audience im Payload des JWT enthalten ist.
                // Ist sie enthalten, wird ein JWTPrincipal erstellt, der die Payload-Daten enthält, sonst null.
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
