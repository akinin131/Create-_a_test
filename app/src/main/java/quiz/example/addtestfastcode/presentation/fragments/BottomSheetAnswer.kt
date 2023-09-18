package quiz.example.addtestfastcode.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.databinding.FragmentBottomSheetAnswerBinding

class BottomSheetAnswer : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetAnswerBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomSheetAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBottomSheetAnswerBinding.bind(view)
        updateAnswerText(answer1)
    }

    fun updateAnswerText(answerText: String) {
        binding.textViewAnswer.text = answerText
    }

    companion object Singleton {
        var answer1 = ""
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

