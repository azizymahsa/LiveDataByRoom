package azizy.mahsa.livedatabyroom.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import azizy.mahsa.livedatabyroom.MainActivity
import azizy.mahsa.livedatabyroom.databinding.FragmentAddNoteBinding
import azizy.mahsa.livedatabyroom.databinding.FragmentNotesListBinding
import azizy.mahsa.livedatabyroom.db.NoteEntity

class AddNoteFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentAddNoteBinding

    //Others
    private lateinit var note: NoteEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()
                note = NoteEntity(0,title,desc)
                (activity as MainActivity).noteDb.noteData().saveNote(note)
                findNavController().popBackStack()
            }
        }
    }
}