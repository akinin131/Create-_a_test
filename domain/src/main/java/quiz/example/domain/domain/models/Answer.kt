package quiz.example.domain.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Answer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val id: Long = 0,
    val text: String,
    @ColumnInfo(name = "questionId")
    val questionId: Long
) {
    fun copyWithText(newText: String) = copy(text = newText)
}