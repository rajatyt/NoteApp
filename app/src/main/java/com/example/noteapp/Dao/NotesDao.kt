package com.example.noteapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Notes

@Dao
interface NotesDao {
	@Query("Select * from Notes")
	fun getNotes(): LiveData<List<Notes>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertNotes(notes: Notes)

	@Query("Delete from Notes where id=:id")
	fun deleteNotes(id:Int)

	@Update
	fun updateNotes(notes: Notes)
}