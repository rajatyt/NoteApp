package com.example.noteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp.Repo.NotesRepo
import com.example.noteapp.database.NotesDatabase
import com.example.noteapp.model.Notes

class NotesViewModel(application: Application) : AndroidViewModel(application) {
	private val repository: NotesRepo

	init {
		val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
		repository = NotesRepo(dao)
	}

	fun getNotes(): LiveData<List<Notes>> {
		return repository.getAllNotes()
	}

	fun addNotes(notes: Notes) {
		repository.insertNotes(notes)
	}

	fun deleteNotes(id: Int) {
		repository.deleteNotes(id)
	}

	fun updateNotes(notes: Notes) {
		repository.updateNotes(notes)
	}
}