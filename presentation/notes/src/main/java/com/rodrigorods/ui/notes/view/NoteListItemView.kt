package com.rodrigorods.ui.notes.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.R
import com.rodrigorods.ui.notes.databinding.NoteListItemBinding

class NoteListItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = NoteListItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(note: Note) {
        binding.noteTitle.text = note.title.ifEmpty(context, R.string.default_title)
        binding.noteDescription.text =
            note.description.ifEmpty(context, R.string.default_description)
    }

    fun obfuscateContent(obfuscate: Boolean) {
        binding.noteTitle.hideData(obfuscate)
        binding.noteDescription.hideData(obfuscate)
    }

    private fun String.ifEmpty(context: Context, resId: Int): String {
        return this.ifEmpty { context.getString(resId) }
    }

    private fun TextView.hideData(obfuscateEntries: Boolean) {
        val inputType =
            if (obfuscateEntries) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
        this.inputType = inputType
    }
}