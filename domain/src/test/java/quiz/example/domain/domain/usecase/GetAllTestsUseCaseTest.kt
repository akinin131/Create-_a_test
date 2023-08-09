package quiz.example.domain.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import quiz.example.domain.domain.repository.TestRepository

class GetAllTestsUseCaseTest {

    @Test
    fun `test execute`() = runBlocking {
        // Создаем mock для зависимости TestRepository
        val testRepository: TestRepository = mock()

        // Создаем экземпляр класса GetAllTestsUseCase с mock-объектом в качестве зависимости
        val getAllTestsUseCase = GetAllTestsUseCase(testRepository)

        // Создаем список тестов, который мы ожидаем получить от mock-объекта testRepository
        val expectedTests = listOf(
            quiz.example.domain.domain.models.Test(id = 1, name = "Test 1", questions = emptyList()),
            quiz.example.domain.domain.models.Test(id = 2, name = "Test 2", questions = emptyList()),
            // и так далее...
        )

        // Задаем поведение mock-объекта при вызове метода getAllTests
        `when`(testRepository.getAllTests()).thenReturn(expectedTests)

        // Вызываем метод execute у GetAllTestsUseCase
        val actualTests = getAllTestsUseCase.execute()

        // Проверяем, что метод getAllTests был вызван у mock-объекта testRepository
        verify(testRepository).getAllTests()

        // Проверяем, что возвращенный список тестов совпадает с ожидаемым
        assertEquals(expectedTests, actualTests)
    }
}
