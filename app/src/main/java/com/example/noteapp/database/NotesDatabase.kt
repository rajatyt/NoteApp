package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.Dao.NotesDao
import com.example.noteapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
	abstract fun myNotesDao(): NotesDao

	companion object {
		var Instance: NotesDatabase? = null

		fun getDatabaseInstance(context: Context): NotesDatabase {
			val tempInstance = Instance
			if (tempInstance != null) {
				return tempInstance
			}
			synchronized(this) {
				val roomDatabaseInstance = Room.databaseBuilder(context, NotesDatabase::class.java, "Notes")
						.allowMainThreadQueries().build()
				Instance = roomDatabaseInstance
				return roomDatabaseInstance
			}
		}
	}
}