package com.example.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,
    @ColumnInfo(name = "note_details")
    val noteDetails: String
)