package com.example.noteapp.ui.Fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.model.Notes
import com.example.noteapp.ui.Adapter.NotesAdapter
import com.example.noteapp.viewModel.NotesViewModel


class HomeFragment : Fragment() {
	val viewModel: NotesViewModel by viewModels()
	lateinit var binding: FragmentHomeBinding
	var myOldNotes= arrayListOf<Notes>()
	lateinit var DataAdapter: NotesAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
		setHasOptionsMenu(true)

//		val StaggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
//		binding.rcy.layoutManager=StaggeredGridLayoutManager

		viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
			binding.rcy.layoutManager = GridLayoutManager(requireContext(), 2)
			myOldNotes = notesList as ArrayList<Notes>
			DataAdapter=NotesAdapter(requireContext(), notesList) /* = java.util.ArrayList<com.example.noteapp.model.Notes> */
			binding.rcy.adapter = DataAdapter


		}


		binding.FloatingActionBtn.setOnClickListener {
			Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
		}
		return binding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.search,menu)
		val item=menu.findItem(R.id.app_bar_search)
		val searchView=item.actionView as SearchView
		searchView.queryHint="Enter Notes here...."
		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				return false
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				NotesFiltering(newText)
				return true
			}



		})
		super.onCreateOptionsMenu(menu, inflater)
	}
	private fun NotesFiltering(newText: String?) {
		val newFilter= arrayListOf<Notes>()
		for (i in myOldNotes){
			if(i.title.contains(newText!!) || i.subtitile.contains(newText!!)){
				newFilter.add(i)
			}
		}
		DataAdapter.filtering(newFilter)
	}



}