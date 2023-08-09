package quiz.example.domain.domain.repository

import quiz.example.domain.domain.models.Answer

interface AnswerRepository {
    suspend fun addAnswer(answer: Answer) : Long

    suspend fun getAnswersForQuestion(questionId: Long): List<Answer>
}