package quiz.example.domain.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val id: Long = 0,
    val text: String,
    @ColumnInfo(name = "questionId") // Add the questionId column
    val questionId: Long
)
