package com.rodrigorods.injector.notes

import com.rodrigorods.data.notes.repository.NotesRepositoryImpl
import com.rodrigorods.domain.notes.repository.NoteRepository
import com.rodrigorods.domain.notes.usecase.NoteUseCase
import com.rodrigorods.domain.notes.usecase.NoteUseCaseImpl
import com.rodrigorods.ui.notes.NotesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.rodrigorods.data.notes.db.provideDatabase
import com.rodrigorods.data.notes.db.provideNotesDao

val uiModule = module {
    viewModelOf(::NotesListViewModel)
}

val domainModule = module {
    factoryOf(::NoteUseCaseImpl) { bind<NoteUseCase>() }
}

val dataModule = module {
    factoryOf(::NotesRepositoryImpl) { bind<NoteRepository>() }
    single { provideDatabase(get()) }
    factory { provideNotesDao(get()) }
}
