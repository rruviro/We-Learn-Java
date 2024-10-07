package com.application.welearnjava.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.welearnjava.LessonFragment
import com.application.welearnjava.Model.Chapter
import com.application.welearnjava.Model.Lessons
import com.application.welearnjava.QuizFragment
import com.application.welearnjava.R

class ChapterAdapter(
    private val chapters: List<Chapter>,
    private val listener: QuizFragment // Use a custom interface instead of AdapterView.OnItemClickListener
) : RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    // ViewHolder class to bind views for each item
    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonTitle: TextView = itemView.findViewById(R.id.lessonTitle)
        val lessonDescription: TextView = itemView.findViewById(R.id.lessonDescrip)

        // Function to bind the click listener to the item view
        fun bind(chapters: Chapter, listener: OnItemClickListener) {
            lessonTitle.text = chapters.title
            lessonDescription.text = chapters.descript

            // Set the click listener for the entire item view
            itemView.setOnClickListener {
                listener.onItemClick(chapters)
            }
        }

    }

    // Custom interface for click events
    interface OnItemClickListener {
        fun onItemClick(chapters: Chapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_items, parent, false)
        return ChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapters = chapters[position]
        holder.bind(chapters, listener) // Pass the listener to the bind method
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

}
