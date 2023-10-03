package com.handearslan.todoapphomework.ui.dailynotes.todos

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.handearslan.todoapphomework.R
import com.handearslan.todoapphomework.data.model.Note
import com.handearslan.todoapphomework.databinding.CardTasarimBinding

class TodoAdapter(
    private val dailyNotesListener: DailyNotesListener
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, dailyNotesListener)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])

    }


    inner class TodoViewHolder(
        private val binding: CardTasarimBinding,
        private val dailyNotesListener: DailyNotesListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Note) {
            with(binding) {
                tvTodo.text = todo.title
                tvChoosePriority.text = todo.priority

                root.setOnClickListener {
                    dailyNotesListener.onNoteClick(todo.title)
                }

                cbCompleted.setOnClickListener {
                    dailyNotesListener.onDeleteClick(todo.id)
                }


                val highPriorityColor =
                    ContextCompat.getColor(tvPriority.context, R.color.highPriorityColor)
                val mediumPriorityColor =
                    ContextCompat.getColor(tvPriority.context, R.color.mediumPriorityColor)
                val lowPriorityColor =
                    ContextCompat.getColor(tvPriority.context, R.color.lowPriorityColor)

                when (todo.priority) {
                    "High" -> cardView.setCardBackgroundColor(highPriorityColor)
                    "Medium" -> cardView.setCardBackgroundColor(mediumPriorityColor)
                    "Low" -> cardView.setCardBackgroundColor(lowPriorityColor)
                }
            }
        }

    }


    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(list: List<Note>, isAdd: Boolean? = null) {
        todoList.clear()
        todoList.addAll(list)

        when (isAdd) {
            true -> notifyItemRangeChanged(0, list.size)
            false -> notifyItemRangeRemoved(0, list.size)
            else -> notifyItemRangeChanged(0, list.size)
        }
    }

    interface DailyNotesListener {
        fun onNoteClick(title: String)
        fun onDeleteClick(id: Int)

    }
}
