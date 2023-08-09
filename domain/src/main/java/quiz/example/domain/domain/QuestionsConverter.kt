package quiz.example.domain.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import quiz.example.domain.domain.models.Question

class QuestionsConverter {
    @TypeConverter
    fun fromQuestionsList(questions: List<Question>): String {
        val gson = Gson()
        return gson.toJson(questions)
    }

    @TypeConverter
    fun toQuestionsList(questionsString: String): List<Question> {
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() {}.type
        return gson.fromJson(questionsString, type)
    }
}
