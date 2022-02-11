package red.courses.mvvm_room_dagger.domain

import red.courses.mvvm_room_dagger.data.QuoteRepository
import red.courses.mvvm_room_dagger.data.database.entities.QuoteEntity
import red.courses.mvvm_room_dagger.data.database.entities.toDatabase
import red.courses.mvvm_room_dagger.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }


    suspend fun addQuotes(listQuotes: List<QuoteEntity>){

        repository.insertQuotes(listQuotes)

    }


}