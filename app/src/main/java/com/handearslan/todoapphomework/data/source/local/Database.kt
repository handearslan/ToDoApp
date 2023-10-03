package com.handearslan.todoapphomework.data.source.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.handearslan.todoapphomework.data.model.Note

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE dailyNotes (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, priority VARCHAR)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) = Unit

    fun addDailyNotes(note: Note) {

        ContentValues().apply {
            put("title", note.title)
            put("priority", note.priority)

            writableDatabase.insert("dailyNotes", null, this)
        }
    }

    fun getDailyNotes(): List<Note> {
        val columns = arrayOf("id", "title", "priority")
        val cursor = readableDatabase.query("dailyNotes", columns, null, null, null, null, null)

        val dailyNotes = mutableListOf<Note>()

        val idIndex = cursor.getColumnIndex("id")
        val titleIndex = cursor.getColumnIndex("title")
        val priorityIndex = cursor.getColumnIndex("priority")

        while (cursor.moveToNext()) {
            dailyNotes.add(
                Note(
                    id = cursor.getInt(idIndex),
                    title = cursor.getString(titleIndex),
                    priority = cursor.getString(priorityIndex)
                )
            )
        }

        return dailyNotes
    }

    fun deleteDailyNotes(id: Int) {
        writableDatabase.delete("dailyNotes", "id=$id", null)
    }

    companion object {
        private const val DATABASE_NAME = "Notes"
        private const val DATABASE_VERSION = 1
    }
}