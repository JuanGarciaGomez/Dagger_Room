package red.courses.mvvm_room_dagger.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import red.courses.mvvm_room_dagger.domain.GetQuotesUseCase
import red.courses.mvvm_room_dagger.domain.GetRandomQuoteUseCase
import red.courses.mvvm_room_dagger.domain.model.Quote
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
) : ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuoteUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            if (quote != null) {
                quoteModel.postValue(quote)
            }
            isLoading.postValue(false)
        }
    }


}