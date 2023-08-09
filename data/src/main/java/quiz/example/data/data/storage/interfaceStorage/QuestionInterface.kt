package quiz.example.data.data.storage.interfaceStorage

import quiz.example.domain.domain.models.Question

interface QuestionInterface {

    suspend fun addQuestion(question: Question) : Long

    suspend fun getQuestionsForTest(testId: Long): List<Question>

    suspend fun deleteQuestionsForTest(delete: Question)

}