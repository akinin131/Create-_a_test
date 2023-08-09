package quiz.example.domain.domain.usecase

import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.repository.TestRepository

class DeleteOneTestUseCase(private val testRepository: TestRepository) {
    suspend fun execute(test: Test, onSuccess: () -> Unit) {
        return testRepository.deleteTest(test) {
            onSuccess()
        }
    }
}