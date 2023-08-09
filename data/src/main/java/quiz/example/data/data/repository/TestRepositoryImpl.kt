package quiz.example.data.data.repository

import quiz.example.data.data.dao.TestDao
import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.repository.TestRepository

class TestRepositoryImpl(private val testDao: TestDao) : TestRepository {

    override suspend fun addTest(test: Test): Long {
        return testDao.insertTest(test)
    }

    override suspend fun getAllTests(): List<Test> {
        return testDao.getAllTests()
    }

    override suspend fun deleteTest(test: Test, onSuccess: () -> Unit) {
        return testDao.delete(test)
    }

}
