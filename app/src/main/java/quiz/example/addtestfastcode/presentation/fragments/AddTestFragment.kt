package quiz.example.addtestfastcode.presentation.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.presentation.adapter.ListTestAdapter
import quiz.example.addtestfastcode.presentation.adapter.QuestionAdapter
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListener
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListenerQuestion
import quiz.example.addtestfastcode.presentation.viewModel.TestViewModel
import quiz.example.domain.domain.models.Question
import quiz.example.domain.domain.models.Test

class AddTestFragment : Fragment() {

    private val viewModel by viewModel<TestViewModel>()
    private lateinit var editTextTestName: EditText
    private lateinit var btnOpenDialog: Button
    private val addedQuestions = mutableListOf<Pair<String, String>>() // Pair<Question, Answer>
    private lateinit var adapter: QuestionAdapter

    private var testId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done -> {
                saveTest()
                requireActivity().onBackPressed()
                return true
            }

            R.id.action_done1 -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_test, container, false)
        editTextTestName = view.findViewById(R.id.editTextTextPersonName)
        btnOpenDialog = view.findViewById(R.id.button)

        btnOpenDialog.setOnClickListener {
            showDialog()
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testsLiveData.observe(viewLifecycleOwner) { tests ->
            testId = tests.maxByOrNull { it.id }?.id?.plus(1) ?: 1
        }

        adapter = QuestionAdapter(emptyList()) // Pass an initial empty list
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewQuestions)
        recyclerView.adapter = adapter

        viewModel.questionsLiveData.observe(viewLifecycleOwner) { questions ->
            adapter.updateQuestions(questions)
        }
        adapter.setOnDeleteClickListener(object : OnDeleteClickListenerQuestion {
            override fun onDeleteClick(question: String) {
                viewModel.deleteQuestion(question) // Передайте текст вопроса для удаления
            }
        })
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.previewdialog)
        val editTextAddQuestion: EditText = dialog.findViewById(R.id.editTextAddQuestion)
        val editTextAnswer: EditText = dialog.findViewById(R.id.editTextAnswer)
        val btnClose: TextView = dialog.findViewById(R.id.btnclose)
        val btnAddToList: Button = dialog.findViewById(R.id.buttondiolog)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        btnAddToList.setOnClickListener {
            val questionText = editTextAddQuestion.text.toString()
            val answerText = editTextAnswer.text.toString()

            if (questionText.isNotEmpty() && answerText.isNotEmpty()) {

                addedQuestions.add(Pair(questionText, answerText))

                addedQuestions.map { Pair(it.first, it.second) }
                viewModel.addQuestionAndAnswer(questionText, answerText, testId)
                Toast.makeText(requireContext(), "Вопрос и ответ добавлены", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            } else {
                Toast.makeText(requireContext(), "Введите вопрос и ответ", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun saveTest() {
        val testName = editTextTestName.text.toString()
        viewModel.saveTest(testName)
    }


}