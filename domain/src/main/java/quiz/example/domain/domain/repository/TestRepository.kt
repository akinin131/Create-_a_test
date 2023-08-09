package quiz.example.domain.domain.repository

import quiz.example.domain.domain.models.Test

interface TestRepository {
    suspend fun addTest(test: Test): Long
    // Update the return type to Long or Long?
    suspend fun getAllTests(): List<Test>

    suspend fun deleteTest(test: Test, onSuccess:()-> Unit)
}
