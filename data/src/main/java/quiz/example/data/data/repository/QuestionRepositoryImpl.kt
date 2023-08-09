package quiz.example.data.data.repository

import quiz.example.data.data.storage.interfaceStorage.QuestionInterface
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.repository.QuestionRepository


class QuestionRepositoryImpl(private val AnswerQuestion: QuestionInterface) : QuestionRepository {
    override suspend fun addQuestion(question: Question): Long {
        return (AnswerQuestion.addQuestion(question))
    }

    override suspend fun getQuestionsForTest(testId: Long): List<Question> {
        return AnswerQuestion.getQuestionsForTest(testId)
    }
    override suspend fun deleteQuestion(question: Question, onSuccess: () -> Unit) {
        return AnswerQuestion.deleteQuestionsForTest(question)
    }
}
