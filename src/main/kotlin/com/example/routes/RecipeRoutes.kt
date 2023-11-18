package com.example.routes

import com.example.data.model.Recipe
import com.example.data.repository.RecipeRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Routen für Rezepte
fun Route.recipeRoutes() {
    // Erstellt eine Instanz von RecipeRepository.
    val recipeRepository = RecipeRepository()

    // Definiert eine Route /rezepte.
    route("/rezepte") {
        // POST-Route für /rezepte.
        post {
            // Empfängt das Rezeptobjekt aus dem Request-Body.
            val recipe = call.receive<Recipe>()
            // Ruft die Methode erstelleRezept des Repository auf, um das Rezept zu speichern.
            val newRecipe = recipeRepository.erstelleRezept(recipe.name, recipe.beschreibung)

            // Überprüft, ob das Rezept erfolgreich erstellt wurde.
            if (newRecipe != null) {
                // Sendet eine Antwort mit dem Statuscode 201 (Created) und dem erstellten Rezept.
                call.respond(HttpStatusCode.Created, newRecipe)
            } else {
                // Sendet eine Fehlerantwort, wenn das Rezept nicht erstellt werden konnte.
                call.respond(HttpStatusCode.InternalServerError, "Failed to create recipe")
            }
        }

        // GET-Route für /rezepte.
        get {
            // Ruft alle Rezepte aus dem Repository ab und sendet sie als Antwort.
            call.respond(recipeRepository.getAllRecipes())
        }

        // GET-Route für /rezepte/{rezeptId}.
        get("{rezeptId}") {
            // Extrahiert die rezeptId aus dem URL-Parameter.
            val recipeIdParam = call.parameters["rezeptId"]
            val recipeId = recipeIdParam?.toIntOrNull()

            // Überprüft, ob die rezeptId gültig ist.
            if (recipeId != null) {
                // Ruft das spezifische Rezept anhand der ID ab.
                val recipe = recipeRepository.getRecipe(recipeId)

                // Überprüft, ob das Rezept gefunden wurde.
                if (recipe != null) {
                    // Sendet das gefundene Rezept als Antwort.
                    call.respond(recipe)
                } else {
                    // Sendet eine Fehlermeldung, wenn das Rezept nicht gefunden wurde.
                    call.respond(HttpStatusCode.NotFound, "Recipe not found")
                }
            } else {
                // Sendet eine Fehlermeldung, wenn die rezeptId ungültig ist.
                call.respond(HttpStatusCode.BadRequest, "Invalid Recipe ID")
            }
        }

        put("{rezeptId}") {
            val recipeId = call.parameters["rezeptId"]?.toIntOrNull()
            val updateRecipeDTO = call.receive<Recipe>()

            if (recipeId != null) {
                val updatedRecipe = recipeRepository.updateRecipe(
                    recipeId,
                    updateRecipeDTO.name,
                    updateRecipeDTO.beschreibung
                )
                if (updatedRecipe != null) {
                    call.respond(HttpStatusCode.OK, updatedRecipe)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Recipe not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid Recipe ID")
            }
        }

        delete("{rezeptId}") {
            val recipeId = call.parameters["rezeptId"]?.toIntOrNull()

            if (recipeId != null) {
                val isDeleted = recipeRepository.deleteRecipe(recipeId)
                if (isDeleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Recipe not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid Recipe ID")
            }
        }

    }
}