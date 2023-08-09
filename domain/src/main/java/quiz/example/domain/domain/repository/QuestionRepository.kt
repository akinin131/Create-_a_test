package quiz.example.domain.domain.repository

import quiz.example.domain.domain.models.Question

interface QuestionRepository {

    suspend fun addQuestion(question: Question) : Long

    suspend fun deleteQuestion(question: Question, onSuccess: () -> Unit)

    suspend fun getQuestionsForTest(testId: Long): List<Question>
}