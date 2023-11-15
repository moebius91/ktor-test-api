package com.example.data.model

import kotlinx.serialization.Serializable

// Markiert die Klasse als serialisierbar.
// Die @Serializable-Annotation ist Teil der kotlinx.serialization-Bibliothek
// und ermöglicht automatische Serialisierung und Deserialisierung dieser Klasse.
@Serializable
// Definiert eine Datenklasse namens RecipesWrapper.
// Datenklassen in Kotlin sind speziell für Klassen konzipiert, die hauptsächlich zum Halten von Daten dienen.
data class RecipesWrapper(
    // Definiert ein Feld namens 'rezepte', das eine Liste von Recipe-Objekten enthält.
    // Dies ermöglicht es, eine Liste von Rezepten als JSON-Objekt zu serialisieren oder zu deserialisieren,
    // wobei die Liste unter dem Schlüssel 'rezepte' gespeichert wird.
    val rezepte: List<Recipe>
)
