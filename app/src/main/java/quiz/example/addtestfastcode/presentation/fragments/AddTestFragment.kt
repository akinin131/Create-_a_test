package quiz.example.addtestfastcode.presentation.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.databinding.FragmentAddTestBinding
import quiz.example.addtestfastcode.presentation.adapter.QuestionAdapter
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListenerQuestion
import quiz.example.addtestfastcode.presentation.viewModel.TestViewModel

class AddTestFragment : Fragment() {

    private val viewModel by viewModel<TestViewModel>()
    private var _binding: FragmentAddTestBinding? = null
    private val binding get() = _binding!!
    private val addedQuestions = mutableListOf<Pair<String, String>>()
    private lateinit var adapter: QuestionAdapter

    private var testId: Long = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddTestBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            showDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_fragment, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return when (menuItem.itemId) {
                    R.id.action_done -> {
                        if (addedQuestions.isEmpty()) {

                            Toast.makeText(requireContext(),"Добавьте хотя бы 1 вопрос", Toast.LENGTH_LONG).show()

                        } else if (binding.editTextTextPersonName.text.isEmpty()){
                            Toast.makeText(requireContext(),"Добавьте название теста", Toast.LENGTH_LONG).show()
                        }else{
                            saveTest()
                            requireActivity().onBackPressed()
                        }
                        return true
                    }
                    R.id.action_done1 -> {
                        requireActivity().onBackPressed()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

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
        val testName = binding.editTextTextPersonName.text.toString()
        viewModel.saveTest(testName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
            lateinit var context: Context
    }

}
