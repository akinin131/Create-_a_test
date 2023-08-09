package quiz.example.data.data.storage.interfaceStorage

import quiz.example.domain.domain.models.Answer

interface AnswerInterface {
    suspend fun addAnswer(answer: Answer)
    suspend fun getAnswersForQuestion(questionId: Long): List<Answer>
}