package quiz.example.addtestfastcode.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import quiz.example.domain.domain.models.Answer
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test
import quiz.example.domain.domain.usecase.DeleteOneTestUseCase
import quiz.example.domain.domain.usecase.AddTestUseCase
import quiz.example.domain.domain.usecase.DeleteOneQuestionUseCase
import quiz.example.domain.domain.usecase.GetAllTestsUseCase

class TestViewModel(
    private val addTestUseCase: AddTestUseCase,
    private val getAllTestsUseCase: GetAllTestsUseCase,
    private val deleteOneTestUseCase: DeleteOneTestUseCase,
    private val deleteOneQuestionUseCase: DeleteOneQuestionUseCase
) : ViewModel() {

    private val _testIdLiveData = MutableLiveData<Long>()
    val testIdLiveData: LiveData<Long> get() = _testIdLiveData

    private val _testsLiveData = MutableLiveData<List<Test>>()
    val testsLiveData: LiveData<List<Test>> get() = _testsLiveData

    private val _questionsLiveData = MutableLiveData<List<String>>()
    val questionsLiveData: LiveData<List<String>> get() = _questionsLiveData

    // Общий список для хранения вопросов и ответов
    private val questionAnswerList = mutableListOf<Pair<String, String>>()
    private val questionList = mutableListOf<String>()

    fun addQuestionAndAnswer(questionText: String, answerText: String, testId: Long) {
        // Добавляем вопрос и ответ в общий список
        questionAnswerList.add(Pair(questionText, answerText))
        questionList.add(questionText)

        _questionsLiveData.postValue(questionList)
    }

    fun saveTest(testName: String) {
        val questionsList = questionAnswerList.mapIndexed { index, (questionText, answerText) ->
            Question(text = questionText,
                testId = null,
                answer = Answer(text = answerText, questionId = index.toLong()))
        }

        viewModelScope.launch {
            val test = Test(name = testName, questions = questionsList)
            val testId = addTestUseCase.execute(test)
            _testIdLiveData.postValue(testId)
        }
    }

    fun getAllTests() {
        viewModelScope.launch {
            val tests = getAllTestsUseCase.execute()
            _testsLiveData.postValue(tests)
        }
    }

    fun getDeleteTest(testName: Test) {
        viewModelScope.launch {
            deleteOneTestUseCase.execute(testName) {
                getAllTests()
            }
        }
    }

    fun deleteQuestion(questionText: String) {
        questionAnswerList.removeAll { it.first == questionText }
        questionList.remove(questionText)
        _questionsLiveData.postValue(questionList)
    }
}