package quiz.example.addtestfastcode.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.databinding.ItemBinding
import quiz.example.addtestfastcode.presentation.fragments.ListTestFragment
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListener
import quiz.example.domain.domain.models.Test
import java.util.*

class ListTestAdapter : RecyclerView.Adapter<ListTestAdapter.TestViewHolder>() {

    private var listTest = emptyList<Test>()

    private var onDeleteClickListener: OnDeleteClickListener? = null

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBinding.bind(itemView)

        fun bind(test: Test, onDeleteClickListener: OnDeleteClickListener?) {
            binding.apply {
                textViewQuestion.text = test.name

                delete.setOnClickListener {
                    onDeleteClickListener?.onDeleteClick(test)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TestViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val currentTest = listTest[position]

        holder.bind(currentTest, onDeleteClickListener)

    }

    override fun getItemCount(): Int {
        return listTest.size
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }

    override fun onViewAttachedToWindow(holder: TestViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener {
            ListTestFragment.clickNote(listTest[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: TestViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newTests: List<Test>) {
        listTest = newTests
        notifyDataSetChanged()
    }
}
