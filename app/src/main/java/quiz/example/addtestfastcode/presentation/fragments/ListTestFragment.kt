package quiz.example.addtestfastcode.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import quiz.example.addtestfastcode.APP
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.databinding.FragmentListTestBinding
import quiz.example.addtestfastcode.presentation.adapter.ListTestAdapter
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListener
import quiz.example.addtestfastcode.presentation.viewModel.TestViewModel
import quiz.example.domain.domain.models.Test

class ListTestFragment : Fragment() {
    private lateinit var binding: FragmentListTestBinding
    private val viewModel by viewModel<TestViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListTestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {

        adapter = ListTestAdapter()
        recyclerView = binding.recyclerViewQuestions
        recyclerView.adapter = adapter
        viewModel.getAllTests()

        viewModel.testsLiveData.observe(viewLifecycleOwner) { tests ->
            adapter.setList(tests)
        }

        binding.btnAddNewTest.setOnClickListener {
            APP.navController.navigate(R.id.action_listTestFragment_to_addTestFragment)
        }

        adapter.setOnDeleteClickListener(object : OnDeleteClickListener {
            override fun onDeleteClick(test: Test) {
                viewModel.getDeleteTest(test)
                viewModel.getAllTests()
            }
        })
    }

    companion object{
        fun clickNote(test: Test){
            val bundle = Bundle()
            bundle.putSerializable("test",test)
            APP.navController.navigate(R.id.action_listTestFragment_to_passingTheTest,bundle)

        }
    }

}