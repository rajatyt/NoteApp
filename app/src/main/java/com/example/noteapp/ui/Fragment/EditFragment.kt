package com.example.noteapp.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentEditBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditFragment : Fragment() {

	val oldNotes by navArgs<EditFragmentArgs>()
	lateinit var binding: FragmentEditBinding
	var priority = "1"
	val viewModel: NotesViewModel by viewModels()


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentEditBinding.inflate(layoutInflater, container, false)
		setHasOptionsMenu(true)

		binding.etEditTitle.setText(oldNotes.data.title)
		binding.etEditSubtitle.setText(oldNotes.data.subtitile)
		binding.etEditNotes.setText(oldNotes.data.notes)

		when (oldNotes.data.priority) {

			"1" -> {
				priority = "1"
				binding.yellowDot.setImageResource(R.drawable.ic_check)
				binding.redDot.setImageResource(0)
				binding.blueDot.setImageResource(0)
			}
			"2" -> {
				priority = "2"
				binding.blueDot.setImageResource(R.drawable.ic_check)
				binding.redDot.setImageResource(0)
				binding.yellowDot.setImageResource(0)
			}
			"3" -> {
				priority = "3"
				binding.redDot.setImageResource(R.drawable.ic_check)
				binding.yellowDot.setImageResource(0)
				binding.blueDot.setImageResource(0)
			}
		}

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

		binding.createEditActionBtn.setOnClickListener {
			updateNotes(it)
		}



		return binding.root
	}

	private fun updateNotes(it: View?) {


		val title = binding.etEditTitle.text.toString()
		val subtitle = binding.etEditSubtitle.text.toString()
		val notes = binding.etEditNotes.text.toString()
		val d = Date()
		val noteDates: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

		val data = Notes(
			oldNotes.data.id, title, subtitle,
			notes, dates = noteDates.toString(),
			priority
		)
		viewModel.updateNotes(data)
		Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.delete_menu, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.menu_delete) {
			val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
			bottomSheet.setContentView(R.layout.delete_notes)
			bottomSheet.show()

			val textViewYes = bottomSheet.findViewById<TextView>(R.id.tv_yes)
			val textViewNo = bottomSheet.findViewById<TextView>(R.id.tv_no)

			textViewYes?.setOnClickListener {
				viewModel.deleteNotes(oldNotes.data.id!!)
				bottomSheet.dismiss()
//				Navigation.findNavController(it).navigate(R.id.action_editFragment_to_homeFragment)
//				navigate(it)
			}
			textViewNo?.setOnClickListener{
				bottomSheet.dismiss()
//				Navigation.findNavController(it).navigate(R.id.action_editFragment_to_homeFragment)

			}

		}
		return super.onOptionsItemSelected(item)

	}

	private fun navigate(it: View?) {
		Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)

	}


}