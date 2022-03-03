package red.courses.mvvm_room_dagger.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import red.courses.mvvm_room_dagger.data.QuoteRepository
import red.courses.mvvm_room_dagger.domain.model.Quote

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    /**
     * Para utilizar la etiqueta @BeforeClass y @AfterClass deben estar dentro de un
     * companion object ademas de tener la etiqueta @JmvStatic para que efectivamente
     * el metodo sea utilizado sin dicha etiqueta no sera llamado.
     */

    companion object{
        @BeforeClass @JvmStatic
        fun setUpCommon(){

        }
        @AfterClass @JvmStatic
        fun tearDownCommon(){

        }
    }

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when the datebase return null or empty then return null`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `when the database is not empty then return quote`() = runBlocking {
        //Given
        val quoteList = listOf(Quote("Sin palabras", "Zarco"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == quoteList.first())


    }

}