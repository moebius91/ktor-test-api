package com.example.data.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Annotation, die diesen Serializer als den benutzerdefinierten Serializer für LocalDateTime-Objekte kennzeichnet.
@Serializer(forClass = LocalDateTime::class)
// Definiert einen Singleton-Serializer für LocalDateTime-Objekte.
object LocalDateTimeSerializer: KSerializer<LocalDateTime> {
    // Ein DateTimeFormatter, der das ISO-8601-Format für Datum und Zeit verwendet.
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // Beschreibt die Eigenschaften des Serialisierers.
    // Hier wird angegeben, dass der Serialisierer Datum und Zeit als String behandelt.
    override val descriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    // Funktion zum Serialisieren eines LocalDateTime-Objekts in einen String.
    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        // Wandelt das LocalDateTime-Objekt in einen String um und gibt diesen an den Encoder weiter.
        encoder.encodeString(value.format(formatter))
    }

    // Funktion zum Deserialisieren eines Strings zurück in ein LocalDateTime-Objekt.
    override fun deserialize(decoder: Decoder): LocalDateTime {
        // Liest einen String aus dem Decoder, interpretiert diesen als LocalDateTime und gibt das resultierende Objekt zurück.
        return LocalDateTime.parse(decoder.decodeString(), formatter)
    }
}
