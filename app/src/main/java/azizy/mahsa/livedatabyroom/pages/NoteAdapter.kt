package azizy.mahsa.livedatabyroom.pages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import azizy.mahsa.livedatabyroom.databinding.ItemNotesBinding
import azizy.mahsa.livedatabyroom.db.NoteEntity


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    private lateinit var binding: ItemNotesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(differ.currentList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemEntity: NoteEntity) {
            binding.apply {
                titleTxt.text = itemEntity.title
                descTxt.text = itemEntity.desc
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
}