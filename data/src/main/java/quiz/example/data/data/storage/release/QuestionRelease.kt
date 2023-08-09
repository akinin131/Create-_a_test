package quiz.example.data.data.storage.release

import quiz.example.data.data.dao.QuestionDao
import quiz.example.data.data.storage.interfaceStorage.QuestionInterface
import quiz.example.domain.domain.models.Question

class QuestionRelease(private val questionDao: QuestionDao):QuestionInterface {
    override suspend fun addQuestion(question: Question): Long {
        return questionDao.insertQuestion(question)
    }

    override suspend fun getQuestionsForTest(testId: Long): List<Question> {
        return questionDao.getQuestionsForTest(testId)
    }
    override suspend fun deleteQuestionsForTest(question: Question) {
        return questionDao.delete(question)
    }
}