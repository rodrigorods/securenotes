package com.rodrigorods.domain.notes.model

data class Note(
    val id : Int,
    val title : String,
    val description : String,
    val creationDate : Long,
)