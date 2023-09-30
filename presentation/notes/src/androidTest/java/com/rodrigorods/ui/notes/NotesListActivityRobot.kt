package com.rodrigorods.ui.notes

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rodrigorods.ui.notes.matcher.matchChildPosition

fun NotesListActivityTest.launch(
    invoke: NotesListActivityRobot.() -> Unit
): NotesListActivityRobot {
    launchActivity<NotesListActivity>().onActivity {  }
    return NotesListActivityRobot().apply(invoke)
}

class NotesListActivityRobot {

    fun clickOnAddNewNotes() {
        onView(withId(R.id.add_note_button)).perform(click(click()))
    }
    fun assertNoteTitleAtPosition(title: String, position: Int) {
        onView(withId(R.id.notes_list)).check(matches(
            matchChildPosition(R.id.note_title, position, withText(title))
        ))
    }
    fun assertNoteDescriptionAtPosition(description: String, position: Int) {
        onView(withId(R.id.notes_list)).check(matches(
            matchChildPosition(R.id.note_description, position, withText(description))
        ))
    }
    fun assertRecyclerViewSize(notesCount: Int) {
        onView(withId(R.id.notes_list)).check(matches(
            hasChildCount(notesCount)
        ))
    }
}
