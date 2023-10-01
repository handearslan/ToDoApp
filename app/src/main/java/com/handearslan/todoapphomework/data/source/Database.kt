package com.handearslan.todoapphomework.data.source


import com.handearslan.todoapphomework.data.model.ToDo

object Database {

    private val todo = mutableListOf<ToDo>()

    fun getTodo(): List<ToDo> {
        return todo
    }

    fun addToDo(title: String, priority: String) {
        val yapilacaklar = ToDo(
            id = (todo.lastOrNull()?.id ?: 0) + 1,
            title = title,
            priority = priority
        )
        todo.add(yapilacaklar)
    }

    fun deleteToDo(toDoDelete: ToDo) {
        todo.remove(toDoDelete)
    }

}
