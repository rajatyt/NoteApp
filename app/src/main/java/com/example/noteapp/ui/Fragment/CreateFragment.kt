package com.example.noteapp.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.viewModel.NotesViewModel
import java.util.*


class CreateFragment : Fragment() {
	lateinit var binding: FragmentCreateBinding
	var priority: String="1"
	val viewModel: NotesViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment

		binding = FragmentCreateBinding.inflate(layoutInflater, container, false)
		binding.yellowDot.setImageResource(R.drawable.ic_check)



		binding.yellowDot.setOnClickListener {
			priority = "1"
			binding.yellowDot.setImageResource(R.drawable.ic_check)
			binding.redDot.setImageResource(0)
			binding.blueDot.setImageResource(0)
		}
		binding.blueDot.setOnClickListener {
			priority = "2"
			binding.blueDot.setImageResource(R.drawable.ic_check)
			binding.redDot.setImageResource(0)
			binding.yellowDot.setImageResource(0)

		}
		binding.redDot.setOnClickListener {
			priority = "3"
			binding.redDot.setImageResource(R.drawable.ic_check)
			binding.yellowDot.setImageResource(0)
			binding.blueDot.setImageResource(0)
		}



		binding.createFloatingActionBtn.setOnClickListener {
			createNotes(it)
		}

		return binding.root
	}

	private fun createNotes(it: View?) {

		val title = binding.etTitle.text.toString()
		val subtitle = binding.etSubtitle.text.toString()
		val notes = binding.etNotes.text.toString()
		val d = Date()
		val noteDates: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

		val data = Notes(
			null, title, subtitle,
			notes, dates = noteDates.toString(),
			priority
		)
		viewModel.addNotes(data)
		Log.d("@@", "createNotes: $noteDates")
		Log.d("@@", "Notes created successfully: $data")

		Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment2)

	}


}