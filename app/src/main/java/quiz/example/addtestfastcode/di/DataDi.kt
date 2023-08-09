package quiz.example.addtestfastcode.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import quiz.example.data.data.db.AppDatabase
import quiz.example.data.data.repository.AnswerRepositoryImpl
import quiz.example.data.data.repository.QuestionRepositoryImpl

import quiz.example.data.data.repository.TestRepositoryImpl
import quiz.example.data.data.storage.interfaceStorage.AnswerInterface
import quiz.example.data.data.storage.interfaceStorage.QuestionInterface
import quiz.example.data.data.storage.release.AnswerRelease
import quiz.example.data.data.storage.release.QuestionRelease
import quiz.example.domain.domain.repository.AnswerRepository
import quiz.example.domain.domain.repository.QuestionRepository
import quiz.example.domain.domain.repository.TestRepository

val dataModule = module {
    // Определите здесь ваши зависимости
    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().testDao() }
    single { get<AppDatabase>().questionDao() }
    single { get<AppDatabase>().answerDao() }

    single<AnswerInterface> { AnswerRelease(answerDao = get()) }
    single<QuestionInterface> { QuestionRelease(questionDao = get()) }

    single<TestRepository> { TestRepositoryImpl(testDao = get()) }
    single<QuestionRepository> { QuestionRepositoryImpl(AnswerQuestion = get()) }
    single<AnswerRepository> { AnswerRepositoryImpl(answerInt = get()) }
}
