package red.courses.mvvm_room_dagger.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import red.courses.mvvm_room_dagger.data.QuoteRepository
import red.courses.mvvm_room_dagger.domain.model.Quote

class GetQuotesUseCaseTest {

    /**
     * Si decimos que es @RelaxedMock y no controlamos la respuesta de una de sus funciones
     * el propio sistema nos dará una por defecto.
     *
     * Por el contrario si seleccionamos solamente @MockK tendremos que configurar
     * cada una de las respuestas que pudiera darnos esa clase mockeada de lo contrario
     * fallara.
     *
     *(LO MEJOR ES USAR @MockK)
     */

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    /**
     * runBlocking{} para lanzar corrutinas
     */

    /**
     * coEvery{}, coVerify{} para corrutinas o suspend fun
     * every{}, verify{} para funcion normal
     * exactly = n, n = al numero de veces que quiero que el metodo sea llamado
     */

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
        coVerify(exactly = 0) { quoteRepository.clearQuotes()  }
        coVerify(exactly = 0) { quoteRepository.insertQuotes(any())  }

    }

    /**
     * El any() ignora parametros dentro de una funcion
     */
    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Quote("EL QUE ESTUDIA TRIUNFA", "Padre"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList

        //When
        val response = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes()  }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any())  }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(myList == response)

    }


}