package quiz.example.domain.domain.models

import androidx.room.*
import quiz.example.domain.domain.QuestionsConverter
import java.io.Serializable

@Entity(tableName = "tests")
@TypeConverters(QuestionsConverter::class)
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val questions: List<Question>
) : Serializable
