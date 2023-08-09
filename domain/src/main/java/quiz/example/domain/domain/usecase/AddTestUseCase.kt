package quiz.example.domain.domain.usecase

import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.repository.AnswerRepository
import quiz.example.domain.domain.repository.QuestionRepository
import quiz.example.domain.domain.repository.TestRepository

class AddTestUseCase(
    private val testRepository: TestRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
) {
    suspend fun execute(test: Test): Long {

        val testId = testRepository.addTest(test)

        test.questions.forEach { question ->
            // Set the testId for the question before adding it
            val questionWithTestId = question.copy(testId = testId)
            val questionId = questionRepository.addQuestion(questionWithTestId)

            // Set the questionId for the answer before adding it
            val answerWithQuestionId = question.answer.copy(questionId = questionId)
            answerRepository.addAnswer(answerWithQuestionId)
        }

        return testId
    }
}



