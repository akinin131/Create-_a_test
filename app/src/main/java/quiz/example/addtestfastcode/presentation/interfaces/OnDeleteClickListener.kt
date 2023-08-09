package quiz.example.addtestfastcode.presentation.interfaces

import quiz.example.domain.domain.models.Test

interface OnDeleteClickListener {
    fun onDeleteClick(test: Test)
}