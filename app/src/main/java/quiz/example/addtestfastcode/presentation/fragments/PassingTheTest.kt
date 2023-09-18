package quiz.example.addtestfastcode.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import quiz.example.addtestfastcode.APP
import quiz.example.addtestfastcode.R

import quiz.example.addtestfastcode.databinding.FragmentPassingTheTestBinding

import quiz.example.addtestfastcode.presentation.fragments.BottomSheetAnswer.Singleton.answer1
import quiz.example.addtestfastcode.presentation.viewModel.PassingTheTestViewModel
import quiz.example.domain.domain.models.Test

class PassingTheTest : Fragment() {

    private var _binding: FragmentPassingTheTestBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModel<PassingTheTestViewModel>()
    private var bottomSheetFragment: BottomSheetAnswer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPassingTheTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AddTestFragment.context = requireContext()

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

                    bottomSheetFragment?.show(requireActivity().supportFragmentManager, bottomSheetFragment?.tag)

                }
            }
            binding.editBtn.setOnClickListener{

                APP.navController.navigate(R.id.action_passingTheTest_to_editTestFragment,bundle)
            }
        }
    }

    companion object{
        fun clickToast(context: Context){
            Toast.makeText(context, "Вопросы закончились", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

