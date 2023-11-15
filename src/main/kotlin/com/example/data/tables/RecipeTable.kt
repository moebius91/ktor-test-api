package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

// Definition der Klasse RecipeTable, die von Table erbt.
// Diese Klasse repräsentiert eine Tabelle in der Datenbank.
object RecipeTable: Table("rezepte") {
    // Definiert eine Spalte 'rezeptId'.
    // Es wird als Integer definiert und automatisch inkrementiert, was typisch für Primärschlüssel ist.
    val rezeptId = integer("rezept_id").autoIncrement()

    // Legt 'rezeptId' als Primärschlüssel für diese Tabelle fest.
    override val primaryKey = PrimaryKey(rezeptId)

    // Definiert eine Spalte 'name' vom Typ VARCHAR mit einer maximalen Länge von 255 Zeichen.
    val name = varchar("name", 255)

    // Definiert eine Spalte 'beschreibung' vom Typ TEXT.
    // Der TEXT-Typ eignet sich gut für längere Textinhalte ohne explizite Längenbeschränkung.
    val beschreibung = text("beschreibung")

    // Definiert eine Spalte 'erstellungsdatum' für Zeitstempel, die den Typ LocalDateTime verwendet.
    // 'clientDefault { LocalDateTime.now() }' setzt automatisch das aktuelle Datum und die aktuelle Uhrzeit
    // als Standardwert, wenn kein anderer Wert angegeben wird.
    val erstellungsdatum = datetime("erstellungsdatum").clientDefault { LocalDateTime.now() }
}
