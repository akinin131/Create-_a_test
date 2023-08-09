package quiz.example.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test


@Dao
interface QuestionDao {
    @Insert
    suspend fun insertQuestion(question: Question): Long

    @Query("SELECT * FROM questions WHERE test_id = :testId")
    suspend fun getQuestionsForTest(testId: Long): List<Question>

    @Delete
    suspend fun delete(questionDelete: Question)

}