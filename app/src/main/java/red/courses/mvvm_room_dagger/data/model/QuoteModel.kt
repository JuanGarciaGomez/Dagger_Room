package red.courses.mvvm_room_dagger.data.model

import com.google.gson.annotations.SerializedName

/**
 * Si nos vemos en la necesidad de crear una clase unicamente para almacenar datos
 * es donde debemos crear una data class
 *
 * Ventajas: Estan pensadas unicamente para almacenar datos por lo que tienen
 * funciones ya pensadas para acceder a los datos sin necesidad de crearlas o extender de alguna otra clase
 *
 */

data class QuoteModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String,
)
