package com.example.data.extensions

import com.example.data.model.Recipe
import com.example.data.tables.RecipeTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.date

// Definiert eine Erweiterungsfunktion 'toRecipe' für das Objekt ResultRow.
// ResultRow ist eine Klasse von Exposed, die eine Zeile aus einer SQL-Abfrage darstellt.
fun ResultRow.toRecipe(): Recipe {
    // Erstellt und gibt eine neue Instanz von Recipe zurück.
    return Recipe(
        // Greift auf den Wert der 'name'-Spalte in der aktuellen Zeile der Abfrage zu
        // und weist diesen dem 'name'-Feld der Recipe-Instanz zu.
        name = this[RecipeTable.name],

        // Greift auf den Wert der 'beschreibung'-Spalte zu
        // und weist diesen dem 'beschreibung'-Feld der Recipe-Instanz zu.
        beschreibung = this[RecipeTable.beschreibung],

        // Greift auf den Wert der 'rezeptId'-Spalte zu
        // und weist diesen dem 'rezeptId'-Feld der Recipe-Instanz zu.
        rezeptId = this[RecipeTable.rezeptId],

        // Greift auf den Wert der 'erstellungsdatum'-Spalte zu
        // und weist diesen dem 'erstellungsdatum'-Feld der Recipe-Instanz zu.
        erstellungsdatum = this[RecipeTable.erstellungsdatum]
    )
}