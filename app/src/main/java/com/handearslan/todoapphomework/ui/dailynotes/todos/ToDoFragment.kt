package com.handearslan.todoapphomework.ui.dailynotes.todos

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.handearslan.todoapphomework.R
import com.handearslan.todoapphomework.common.viewBinding
import com.handearslan.todoapphomework.data.model.Note
import com.handearslan.todoapphomework.data.source.local.Database
import com.handearslan.todoapphomework.databinding.DialogAddTodoBinding
import com.handearslan.todoapphomework.databinding.FragmentToDoBinding

class ToDoFragment : Fragment(R.layout.fragment_to_do) , TodoAdapter.DailyNotesListener {

    private val binding by viewBinding(FragmentToDoBinding::bind)

    private lateinit var db : Database

    private val todoAdapter = TodoAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Database(requireContext())

        with(binding) {
            tbToDo.title = "Yapılacaklar"
            rvToDo.adapter = todoAdapter
            todoAdapter.updateList(db.getDailyNotes())

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
                val title = etDesc.text.toString()
                val priority = when {
                    radioLow.isChecked -> "Low"
                    radioMedium.isChecked -> "Medium"
                    radioHigh.isChecked -> "High"
                    else -> ""
                }
                if (title.isNotEmpty() && priority.isNotEmpty()) {
                    db.addDailyNotes(Note(title, priority,id ))
                    todoAdapter.updateList(db.getDailyNotes(),true)
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Lütfen boş bırakmayınız", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        dialog.show()
    }

    override fun onNoteClick(title: String) {
      Toast.makeText(requireContext(),title,Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(id: Int) {
        db.deleteDailyNotes(id)
        todoAdapter.updateList(db.getDailyNotes(),false)
    }
}
