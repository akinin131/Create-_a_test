package quiz.example.data.data.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import quiz.example.domain.domain.models.Test

@Dao
interface TestDao {
    @Insert
    suspend fun insertTest(test: Test): Long

    @Query("SELECT * FROM tests")
    suspend fun getAllTests(): List<Test>

    @Delete
    suspend fun delete(test: Test)
}

