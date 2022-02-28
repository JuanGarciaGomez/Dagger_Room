package red.courses.mvvm_room_dagger.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import red.courses.mvvm_room_dagger.domain.GetQuotesUseCase
import red.courses.mvvm_room_dagger.domain.GetRandomQuoteUseCase
import red.courses.mvvm_room_dagger.domain.model.Quote

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    /**
     * Experimental... es para quitar warning por el tema de Dispatchers
     */

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    /**
     * Rule viene de arch core
     * funcion en el onBefore pero abstraida
     *
     */

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Dispatchers es para el tema de los hilos/corrutinas en el viewModel
     */

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    /**
     * Resetear los dispatchers
     */
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    /**
     * runTest{}, lo necesitaremos en cada uno de nuestros test del ViewModel.
     */

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest{
        //Given
        val quoteList = listOf(Quote("Holi", "Aris"), Quote("Dame un like", "Otro Aris "))
        coEvery { getQuotesUseCase() } returns quoteList

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val quote = Quote("Holi", "Aris")
        coEvery { getRandomQuoteUseCase() } returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest{
        //Given
        val quote = Quote("Aris", "Aris")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

}