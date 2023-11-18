package com.example.data.repository

import com.example.data.extensions.toRecipe
import com.example.data.model.Recipe
import com.example.data.model.RecipesWrapper
import com.example.data.tables.RecipeTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

// Klasse RecipeRepository zur Verwaltung von Rezeptdaten.
class RecipeRepository {

    // Funktion zum Erstellen eines neuen Rezepts in der Datenbank.
    fun erstelleRezept(name: String, beschreibung: String): Recipe? {
        // Startet eine Transaktion mit der Datenbank.
        return transaction {
            // Fügt einen neuen Eintrag in die RecipeTable ein.
            val rezeptId = RecipeTable.insert {
                // Setzt den Namen und die Beschreibung für das neue Rezept.
                it[RecipeTable.name] = name
                it[RecipeTable.beschreibung] = beschreibung
            }
                .resultedValues // Holt die Ergebniswerte der Einfügung.
                ?.firstOrNull() // Nimmt den ersten Wert, falls vorhanden.
                ?.get(RecipeTable.rezeptId) // Holt die ID des eingefügten Rezepts.

            // Erstellt ein neues Recipe-Objekt, wenn eine ID vorhanden ist, ansonsten gibt es null zurück.
            rezeptId?.let {
                Recipe(
                    it,
                    name,
                    beschreibung,
                    erstellungsdatum = null
                )
            }
        }
    }

    // Funktion, um alle Rezepte aus der Datenbank zu holen.
    fun getAllRecipes(): RecipesWrapper {
        // Umschließt das Ergebnis in einem RecipesWrapper, um das gewünschte JSON-Format zu erzielen.
        return RecipesWrapper(
            transaction {
                // Wählt alle Einträge aus der RecipeTable aus und konvertiert sie in Recipe-Objekte.
                RecipeTable.selectAll().map { it.toRecipe() }
            }
        )
    }

    // Funktion, um ein einzelnes Rezept anhand seiner ID zu holen.
    fun getRecipe(recipeId: Int): Recipe? {
        // Startet eine Transaktion mit der Datenbank.
        return transaction {
            // Wählt das Rezept mit der gegebenen ID aus und konvertiert es in ein Recipe-Objekt.
            // Gibt null zurück, wenn kein Eintrag gefunden wird.
            RecipeTable.select { RecipeTable.rezeptId eq recipeId }.singleOrNull()?.toRecipe()
        }
    }

    // Funktion, um ein einzelnes Rezept anhand seiner ID zu aktualisieren
    fun updateRecipe(recipeId: Int, newName: String, newDescription: String): Recipe? {
        return transaction {
            // Aktualisiert das Rezept mit der gegebenen ID
            RecipeTable.update({ RecipeTable.rezeptId eq recipeId }) {
                it[name] = newName
                it[beschreibung] = newDescription
            }

            RecipeTable.select { RecipeTable.rezeptId eq recipeId }.singleOrNull()?.toRecipe()
        }
    }

    // Funktion, um ein einzelnes Rezept anhand seiner ID zu löschen.
    fun deleteRecipe(recipeId: Int): Boolean {
        return transaction {
            // Verwendet die deleteWhere-Funktion, um das Rezept mit der angegebenen ID zu löschen.
            val deletedRows = RecipeTable.deleteWhere { RecipeTable.rezeptId eq recipeId}
            // Gibt true zurück, wenn mindestens eine Zeile gelöscht wurde.
            deletedRows > 0
        }
    }

}
