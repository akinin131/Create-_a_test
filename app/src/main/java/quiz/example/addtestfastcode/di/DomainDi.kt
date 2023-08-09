package quiz.example.addtestfastcode.di

import org.koin.dsl.module
import quiz.example.domain.domain.usecase.DeleteOneTestUseCase
import quiz.example.domain.domain.usecase.GetAllTestsUseCase
import quiz.example.domain.domain.usecase.AddTestUseCase
import quiz.example.domain.domain.usecase.DeleteOneQuestionUseCase

val domainModule = module {

    single {
        AddTestUseCase(testRepository = get(),
            questionRepository = get(),
            answerRepository = get())
    }

    single { GetAllTestsUseCase(testRepository = get()) }

    single { DeleteOneTestUseCase(testRepository = get()) }
    single { DeleteOneQuestionUseCase( questionRepository= get()) }
}
