package quiz.example.domain.domain.usecase

import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import quiz.example.domain.domain.models.Answer
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.repository.AnswerRepository
import quiz.example.domain.domain.repository.QuestionRepository
import quiz.example.domain.domain.repository.TestRepository

class AddTestUseCaseTest {

    @Test
    fun `test execute`() = runBlocking {
        // Создаем mock-объекты для зависимостей TestRepository, QuestionRepository и AnswerRepository
        val testRepository: TestRepository = mock()
        val questionRepository: QuestionRepository = mock()
        val answerRepository: AnswerRepository = mock()

        // Создаем экземпляр класса AddTestUseCase с mock-объектами в качестве зависимостей
        val addTestUseCase = AddTestUseCase(testRepository, questionRepository, answerRepository)

        // Создаем тестовый объект Test
        val test = quiz.example.domain.domain.models.Test(id = 1, name = "Test 1", questions = listOf(
            Question(id = 1, text = "Question 1", testId = 1, answer = Answer(id = 1, text = "Answer 1", questionId = 1)),
            Question(id = 2, text = "Question 2", testId = 1, answer = Answer(id = 2, text = "Answer 2", questionId = 2))
        ))

        // Задаем поведение mock-объектов при вызове методов addTest, addQuestion и addAnswer
        whenever(testRepository.addTest(test)).thenReturn(1)
        whenever(questionRepository.addQuestion(any())).thenReturn(1)
        whenever(answerRepository.addAnswer(any())).thenReturn(1)

        // Вызываем метод execute у AddTestUseCase
        val testId = addTestUseCase.execute(test)

        // Проверяем, что методы addTest, addQuestion и addAnswer были вызваны с правильными аргументами
        verify(testRepository).addTest(test)
        test.questions.forEach { question ->
            val questionWithTestId = question.copy(testId = 1)
            verify(questionRepository).addQuestion(questionWithTestId)
            val answerWithQuestionId = question.answer.copy(questionId = 1)
            verify(answerRepository).addAnswer(answerWithQuestionId)
        }

        assertEquals(1L, testId)

    }
}
