package com.handearslan.todoapphomework.ui.dailynotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.handearslan.todoapphomework.R
import com.handearslan.todoapphomework.data.model.ToDo
import com.handearslan.todoapphomework.data.source.Database
import com.handearslan.todoapphomework.databinding.CardTasarimBinding

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = mutableListOf<ToDo>()

    inner class TodoViewHolder(private val binding: CardTasarimBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: ToDo) {
            with(binding) {
                tvTodo.text = todo.title
                tvChoosePriority.text = todo.priority
                cbCompleted.setOnClickListener {
                    val position = adapterPosition
                    notifyItemRemoved(position)
                    val removedTodo = todoList.removeAt(position)
                    Database.deleteToDo(removedTodo)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(list: List<ToDo>) {
        todoList.clear()
        todoList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }
}
