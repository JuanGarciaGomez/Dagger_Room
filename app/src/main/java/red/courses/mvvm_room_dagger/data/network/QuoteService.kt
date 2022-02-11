package red.courses.mvvm_room_dagger.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import red.courses.mvvm_room_dagger.core.RetrofitHelper
import red.courses.mvvm_room_dagger.data.model.QuoteModel
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:QuoteApiClient){

    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }

}