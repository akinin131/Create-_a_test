package quiz.example.data.data.storage.release

import quiz.example.data.data.dao.AnswerDao
import quiz.example.data.data.storage.interfaceStorage.AnswerInterface
import quiz.example.domain.domain.models.Answer

class AnswerRelease(private val answerDao: AnswerDao) : AnswerInterface {

    override suspend fun addAnswer(answer: Answer) {
        answerDao.insertAnswer(answer)
    }
    override suspend fun getAnswersForQuestion(questionId: Long): List<Answer> {
        return answerDao.getAnswersForQuestion(questionId)
    }
}