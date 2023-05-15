package com.example.noteapp.ui.Adapter

import android.content.Context
import android.net.wifi.hotspot2.pps.HomeSp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.ItemNotesBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.ui.Fragment.HomeFragment
import com.example.noteapp.ui.Fragment.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewModel>() {

	fun filtering(newFilter: ArrayList<Notes>) {
		notesList=newFilter
		notifyDataSetChanged()

	}
	class notesViewModel(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewModel {
		return notesViewModel(
			ItemNotesBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)

	}

	override fun onBindViewHolder(holder: notesViewModel, position: Int) {
		var data = notesList[position]
		holder.binding.tvTitle.text = data.title
		holder.binding.tvTitleDesc.text = data.subtitile
		holder.binding.tvDate.text = data.dates

		when (data.priority) {

			"1" -> {
				holder.binding.colorPriority.setBackgroundResource(R.drawable.yellow)
			}
			"2" -> {
				holder.binding.colorPriority.setBackgroundResource(R.drawable.blue)

			}
			"3" -> {
				holder.binding.colorPriority.setBackgroundResource(R.drawable.red)

			}
		}

		holder.binding.root.setOnClickListener {
			val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
			Navigation.findNavController(it).navigate(action)
		}
	}

	override fun getItemCount(): Int {
		return notesList.size
	}


}