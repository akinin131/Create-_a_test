package quiz.example.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import quiz.example.domain.domain.models.Answer

@Dao
interface AnswerDao {
    @Insert
    suspend fun insertAnswer(answer: Answer): Long

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    suspend fun getAnswersForQuestion(questionId: Long): List<Answer>

}