package quiz.example.data.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import quiz.example.data.data.dao.AnswerDao
import quiz.example.data.data.dao.QuestionDao
import quiz.example.data.data.dao.TestDao
import quiz.example.domain.domain.models.Answer
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test


@Database(entities = [Test::class, Question::class, Answer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao

    companion object {
        private const val DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
