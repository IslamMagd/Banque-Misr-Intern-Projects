package com.example.notesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.Note
import com.example.notesapp.database.RoomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(app: Application): AndroidViewModel(app) {
     private val db = RoomDBHelper.getInstance(app)

    fun upsert(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            db.dao.upsertNote(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            db.dao.deleteNote(note)
        }
    }

    fun getNotes() = db.dao.getAllNotes()
}