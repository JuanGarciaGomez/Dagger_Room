package red.courses.mvvm_room_dagger.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import red.courses.mvvm_room_dagger.data.database.entities.QuoteEntity
import red.courses.mvvm_room_dagger.domain.GetQuotesUseCase
import javax.inject.Inject

@HiltViewModel
class QuoteRegisterViewModel @Inject constructor(private val getQuoteUseCase: GetQuotesUseCase) :
    ViewModel() {

    fun addQuote(author: String, quote: String) {

        viewModelScope.launch {
            getQuoteUseCase.addQuotes(listOf(QuoteEntity(author = author, quote = quote)))
        }

    }
}