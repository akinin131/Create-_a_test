package quiz.example.addtestfastcode.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import quiz.example.addtestfastcode.presentation.adapter.EditTestAdapter
import quiz.example.addtestfastcode.presentation.viewModel.PassingTheTestViewModel
import quiz.example.addtestfastcode.presentation.viewModel.TestViewModel

val appModule = module {
    viewModel {
        TestViewModel(addTestUseCase = get(),
            getAllTestsUseCase = get(),
            deleteOneTestUseCase = get(),
            deleteOneQuestionUseCase = get()
        )

    }
    viewModel {
        PassingTheTestViewModel()
    }

    single { EditTestAdapter(get()) }
}