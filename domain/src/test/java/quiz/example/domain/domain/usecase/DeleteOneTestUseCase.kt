package quiz.example.domain.domain.usecase

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import quiz.example.domain.domain.repository.TestRepository

class DeleteOneTestUseCaseTest {
    @Test
    fun `test execute`() = runBlocking {
        // Создаем mock для зависимости TestRepository
        val testRepository: TestRepository = mock()

        // Создаем экземпляр класса DeleteOneTestUseCase с mock-объектом в качестве зависимости
        val deleteOneTestUseCase = DeleteOneTestUseCase(testRepository)

        // Создаем тестовый объект Test
        val test = quiz.example.domain.domain.models.Test(id = 1, name = "Test 1", questions = emptyList())

        // Задаем поведение mock-объекта при вызове метода deleteTest
        doAnswer {
            val onSuccessCallback: () -> Unit = it.getArgument(1)
            onSuccessCallback() // Вызываем колбэк при успешном удалении теста
        }.whenever(testRepository).deleteTest(any(), any())

        // Вызываем метод execute у DeleteOneTestUseCase
        deleteOneTestUseCase.execute(test) {
            // Колбэк будет вызван, когда удаление завершится успешно
            // Можно добавить сюда проверки или действия, выполняемые при успешном удалении
        }

        // Проверяем, что метод deleteTest был вызван у mock-объекта testRepository
        verify(testRepository).deleteTest(any(), any()) // Заменяем any(Test::class.java) на any()
    }

}
