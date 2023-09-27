package com.rodrigorods.data.notes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    val creationDate: Long
)