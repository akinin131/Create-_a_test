package quiz.example.domain.domain.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "answer_text")
    val text: String,
    @ColumnInfo(name = "test_id") // Use "test_id" as the column name
    val testId: Long?,
    @Embedded
    val answer: Answer
) : Serializable
{
    fun copy(text: String, answerText: String) =
        copy(text = text, answer = answer.copy(text = answerText))
}