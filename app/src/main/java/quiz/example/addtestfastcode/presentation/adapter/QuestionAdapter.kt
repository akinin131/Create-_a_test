package quiz.example.addtestfastcode.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import quiz.example.addtestfastcode.R
import quiz.example.addtestfastcode.databinding.ItemBinding
import quiz.example.addtestfastcode.presentation.interfaces.OnDeleteClickListenerQuestion
import java.util.*

class QuestionAdapter(private var questions: List<String>) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private lateinit var onDeleteClickListenerQuestion: OnDeleteClickListenerQuestion

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuestion: TextView = itemView.findViewById(R.id.textViewQuestion)
        private val binding = ItemBinding.bind(itemView)
        fun bind(question: String, onDeleteClickListenerQuestion: OnDeleteClickListenerQuestion) {
            binding.apply {

                delete.setOnClickListener {
                    onDeleteClickListenerQuestion.onDeleteClick(question)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.textViewQuestion.text = "Вопрос: $question"

        holder.bind(question, onDeleteClickListenerQuestion)

    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListenerQuestion) {
        onDeleteClickListenerQuestion = listener
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateQuestions(newQuestions: List< String>) {
        questions = newQuestions
        notifyDataSetChanged()
    }
}
