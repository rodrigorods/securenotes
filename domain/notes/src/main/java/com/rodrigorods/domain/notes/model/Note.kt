package com.rodrigorods.domain.notes.model

data class Note(
    val id : Long,
    val title : String = "",
    val description : String = "",
    val creationDate : Long = 0,
)