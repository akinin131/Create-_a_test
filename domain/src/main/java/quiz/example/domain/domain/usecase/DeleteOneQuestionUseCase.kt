package quiz.example.domain.domain.usecase

import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.repository.QuestionRepository

class DeleteOneQuestionUseCase (private val questionRepository: QuestionRepository) {
    suspend fun execute(question: Question, onSuccess: () -> Unit) {
        return questionRepository.deleteQuestion(question) {
            onSuccess()
        }
    }
}