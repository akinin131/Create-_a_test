package quiz.example.domain.domain.usecase

import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.repository.TestRepository

class GetAllTestsUseCase(private val testRepository: TestRepository) {
    suspend fun execute(): List<Test> {
        return testRepository.getAllTests()
    }
}