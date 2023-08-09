package quiz.example.addtestfastcode.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import quiz.example.addtestfastcode.APP
import quiz.example.addtestfastcode.presentation.fragments.PassingTheTest.Companion.clickToast
import quiz.example.domain.domain.models.Answer
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test

class PassingTheTestViewModel :
    ViewModel() {
    private lateinit var test: Test
    private lateinit var questions: List<Question>
    private lateinit var answers: List<Answer>
    private var currentIndex = 0

    private val questionAndAnswerLiveData = MutableLiveData<Pair<Question, Answer>>()
    val questionAndAnswerObservable: LiveData<Pair<Question, Answer>>
        get() = questionAndAnswerLiveData

    fun startTest(test: Test) {
        this.test = test
        questions = test.questions
        answers = questions.map { it.answer }
        showQuestionAndAnswer()
    }

    fun getNextQuestionAndAnswer() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            showQuestionAndAnswer()
        } else {
            clickToast(APP)
        }
    }

    private fun showQuestionAndAnswer() {
        if (currentIndex < questions.size) {
            val question = questions[currentIndex]
            val answer = answers[currentIndex]
            questionAndAnswerLiveData.value = Pair(question, answer)
        }
    }

}



