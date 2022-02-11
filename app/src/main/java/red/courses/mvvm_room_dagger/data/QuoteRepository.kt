package red.courses.mvvm_room_dagger.data

import red.courses.mvvm_room_dagger.data.database.dao.QuoteDao
import red.courses.mvvm_room_dagger.data.database.entities.QuoteEntity
import red.courses.mvvm_room_dagger.data.model.QuoteModel
import red.courses.mvvm_room_dagger.data.network.QuoteService
import red.courses.mvvm_room_dagger.domain.model.Quote
import red.courses.mvvm_room_dagger.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao,
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }


}

