package com.application.welearnjava.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.welearnjava.Model.Documentation
import com.application.welearnjava.R
import com.bumptech.glide.Glide

class DocumentationAdapter(
    private val documentationList: List<Documentation>,
    private val Id: Int,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DocumentationAdapter.DocumentationViewHolder>() {

    private val filteredDocumentationList = documentationList.filter { it.id == Id }

    class DocumentationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.Title)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.imageView2)

        fun bind(documentation: Documentation, listener: OnItemClickListener) {
            title.text = documentation.topic
            description.text = documentation.description
            Glide.with(itemView.context)
                .load(documentation.image) // Use this if the image is a URL
                .into(image)

            itemView.setOnClickListener {
                listener.onItemClick(documentation, this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.documentation_items, parent, false)
        return DocumentationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocumentationViewHolder, position: Int) {
        val documentation = filteredDocumentationList[position]
        holder.bind(documentation, listener)
    }

    override fun getItemCount(): Int {
        return filteredDocumentationList.size
    }

    interface OnItemClickListener {
        fun onItemClick(documentation: Documentation, viewHolder: DocumentationViewHolder)
    }
}
