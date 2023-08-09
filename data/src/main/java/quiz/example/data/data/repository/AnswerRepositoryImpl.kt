package quiz.example.data.data.repository

import quiz.example.data.data.storage.interfaceStorage.AnswerInterface
import quiz.example.domain.domain.models.Answer
import quiz.example.domain.domain.repository.AnswerRepository

class AnswerRepositoryImpl(private val answerInt: AnswerInterface) : AnswerRepository {

    override suspend fun addAnswer(answer: Answer): Long {
        answerInt.addAnswer(answer)
        return answer.id
    }

    override suspend fun getAnswersForQuestion(questionId: Long): List<Answer> {
        return answerInt.getAnswersForQuestion(questionId)
    }
}
