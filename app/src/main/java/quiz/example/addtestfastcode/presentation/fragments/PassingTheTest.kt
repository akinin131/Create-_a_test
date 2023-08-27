package quiz.example.addtestfastcode.presentation.fragments

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import quiz.example.addtestfastcode.databinding.FragmentPassingTheTestBinding

import quiz.example.addtestfastcode.presentation.fragments.BottomSheetAnswer.Singleton.answer1
import quiz.example.addtestfastcode.presentation.viewModel.PassingTheTestViewModel
import quiz.example.domain.domain.models.Test

class PassingTheTest : Fragment() {

    private lateinit var binding: FragmentPassingTheTestBinding
    private lateinit var viewModel: PassingTheTestViewModel
    private var bottomSheetFragment: BottomSheetAnswer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPassingTheTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PassingTheTestViewModel::class.java]

        val bundle = arguments
        if (bundle != null && bundle.containsKey("test")) {

            val test = bundle.getSerializable("test") as Test

            viewModel.questionAndAnswerObservable.observe(viewLifecycleOwner) { (question, answer) ->

                binding.textViewQuestion.text = "Вопрос: ${question.text}"
                answer1 =  answer.text

            }

            viewModel.startTest(test)

            binding.btnNextQuestion.setOnClickListener {

                viewModel.getNextQuestionAndAnswer()

            }

            binding.btnAnswer.setOnClickListener {
                if (bottomSheetFragment == null) {
                    bottomSheetFragment = BottomSheetAnswer()
                }
                if (!bottomSheetFragment!!.isAdded) {
                    // Открываем Bottom Sheet и передаем текст ответа
                    bottomSheetFragment?.show(requireActivity().supportFragmentManager, bottomSheetFragment?.tag)

                }
            }
        }
    }

    companion object{
        fun clickToast(context: Context){
            Toast.makeText(context, "Вопросы закончились", Toast.LENGTH_SHORT).show()
        }
    }
}

