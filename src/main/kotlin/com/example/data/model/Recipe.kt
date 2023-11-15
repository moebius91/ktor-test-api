package com.example.data.model

import com.example.data.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

// Markiert die Klasse als serialisierbar.
// Die @Serializable-Annotation ermöglicht automatische Serialisierung und Deserialisierung dieser Klasse.
@Serializable
// Definiert eine Datenklasse namens Recipe.
// Datenklassen in Kotlin sind speziell für Klassen konzipiert, die hauptsächlich zum Halten von Daten dienen.
data class Recipe(
    // Definiert ein optionalles Feld 'rezeptId' vom Typ Int.
    // Der Standardwert ist null, was bedeutet, dass es optional ist.
    val rezeptId: Int? = null,

    // Definiert ein Feld 'name' vom Typ String.
    val name: String,

    // Definiert ein Feld 'beschreibung' vom Typ String.
    val beschreibung: String,

    // Spezielle Serialisierungsanweisung für das Feld 'erstellungsdatum'.
    // Verwendet den benutzerdefinierten LocalDateTimeSerializer für die Serialisierung und Deserialisierung.
    @Serializable(with = LocalDateTimeSerializer::class)
    val erstellungsdatum: LocalDateTime? = null
)
