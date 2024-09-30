package com.application.welearnjava.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.welearnjava.LessonFragment
import com.application.welearnjava.Model.Lessons
import com.application.welearnjava.R

class LessonAdapter(
    private val lessons: List<Lessons>,
    private val listener: LessonFragment // Use a custom interface instead of AdapterView.OnItemClickListener
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    // ViewHolder class to bind views for each item
    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonTitle: TextView = itemView.findViewById(R.id.lessonTitle)
        val lessonDescription: TextView = itemView.findViewById(R.id.lessonDescrip)

        // Function to bind the click listener to the item view
        fun bind(lesson: Lessons, listener: OnItemClickListener) {
            lessonTitle.text = lesson.title
            lessonDescription.text = lesson.descript

            // Set the click listener for the entire item view
            itemView.setOnClickListener {
                listener.onItemClick(lesson)
            }
        }

    }

    // Custom interface for click events
    interface OnItemClickListener {
        fun onItemClick(lesson: Lessons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_items, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson, listener) // Pass the listener to the bind method
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

}
