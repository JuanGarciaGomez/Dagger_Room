package red.courses.mvvm_room_dagger.domain.model

import red.courses.mvvm_room_dagger.data.database.entities.QuoteEntity
import red.courses.mvvm_room_dagger.data.model.QuoteModel

data class Quote(val quote: String, val author: String)


fun QuoteModel.toDomain() = Quote(quote,author)
fun QuoteEntity.toDomain() = Quote(quote,author)