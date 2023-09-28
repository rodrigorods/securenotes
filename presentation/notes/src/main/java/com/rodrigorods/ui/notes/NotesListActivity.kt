package com.rodrigorods.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodrigorods.ui.notes.databinding.ActivityNotesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesListBinding

//    private val viewModel: NotesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
