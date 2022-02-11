package red.courses.mvvm_room_dagger.data.network

import red.courses.mvvm_room_dagger.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}