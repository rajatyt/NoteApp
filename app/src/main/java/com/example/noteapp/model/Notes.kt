package com.example.noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
@Entity(tableName = "Notes")
data class Notes(
	@PrimaryKey(autoGenerate = true)
	var id: Int? = null,
	var title: String,
	var subtitile: String,
	var notes: String,
	var dates: String,
	var priority: String
):Parcelable
