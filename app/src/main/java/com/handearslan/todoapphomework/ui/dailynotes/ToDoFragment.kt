package com.handearslan.todoapphomework.ui.dailynotes

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.handearslan.todoapphomework.R
import com.handearslan.todoapphomework.common.viewBinding
import com.handearslan.todoapphomework.data.source.Database
import com.handearslan.todoapphomework.databinding.DialogAddTodoBinding
import com.handearslan.todoapphomework.databinding.FragmentToDoBinding

class ToDoFragment : Fragment(R.layout.fragment_to_do) {

    private val binding by viewBinding(FragmentToDoBinding::bind)
    private val todoAdapter = TodoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tbToDo.title = "Yapılacaklar"
            rvToDo.adapter = todoAdapter
            todoAdapter.updateList(Database.getTodo())

            fab.setOnClickListener {
                showAddDialog()
            }
        }
    }

    private fun showAddDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogAddTodoBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val dialog = builder.create()

        with(dialogBinding) {

            btnAddNote.setOnClickListener {
                val desription = etDesc.text.toString()
                val priority = when {
                    radioLow.isChecked -> "Low"
                    radioMedium.isChecked -> "Medium"
                    radioHigh.isChecked -> "High"
                    else -> ""
                }
                if (etDesc.text.isNotEmpty() || priority.isNotEmpty()) {
                    Database.addToDo(desription, priority)
                    todoAdapter.updateList(Database.getTodo())
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Lütfen boş bırakmayınız", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            dialog.show()
        }
    }
}
