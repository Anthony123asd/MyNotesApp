package com.dicoding.kotlin.mynotesapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.kotlin.mynotesapp.CustomOnItemClickListener
import com.dicoding.kotlin.mynotesapp.NoteAddUpdateActivity
import com.dicoding.kotlin.mynotesapp.R
import com.dicoding.kotlin.mynotesapp.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var listNotes = ArrayList<Note?>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
            notifyDataSetChanged()
        }

    fun addItem(note: Note?) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note?) {
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int) {
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            with(itemView) {
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
                cv_item_note.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                            }

                        })
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        listNotes[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

}