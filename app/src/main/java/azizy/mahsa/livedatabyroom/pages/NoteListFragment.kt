package azizy.mahsa.livedatabyroom.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import azizy.mahsa.livedatabyroom.MainActivity
import azizy.mahsa.livedatabyroom.R
import azizy.mahsa.livedatabyroom.databinding.FragmentNotesListBinding

class NoteListFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentNotesListBinding

    //Other
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //click
            addNoteBtn.setOnClickListener {
                findNavController().navigate(R.id.actionListtoAdd)
            }
            //Get data
            (activity as MainActivity).noteDb.noteData().getAllNotes().observe(viewLifecycleOwner) {
                noteAdapter.differ.submitList(it)
                notesRecycler.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter=noteAdapter
                }
            }
        }
    }
}