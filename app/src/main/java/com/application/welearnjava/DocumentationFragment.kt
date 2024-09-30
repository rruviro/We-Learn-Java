package com.application.welearnjava

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.welearnjava.Adapter.DocumentationAdapter
import com.application.welearnjava.Model.Documentation
import com.application.welearnjava.databinding.FragmentDocumentationBinding
import org.w3c.dom.Text

class DocumentationFragment : Fragment(), DocumentationAdapter.OnItemClickListener {

    private lateinit var documentationAdapter: DocumentationAdapter
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDocumentationBinding.inflate(inflater, container, false)

        // Get the Id passed through arguments
        val Id = arguments?.getString("Id") ?: return binding.root
        // Initialize your adapter with the documentation list
        documentationAdapter = DocumentationAdapter(Documentation.documentation, Id.toInt(), this)

        // Set the adapter to the RecyclerView
        binding.documentationRecycle.adapter = documentationAdapter
        binding.documentationRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.documentationRecycle.isNestedScrollingEnabled = true

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onItemClick(documentation: Documentation, viewHolder: DocumentationAdapter.DocumentationViewHolder) {
        toggleDetailsLayout(documentation.height, viewHolder) // Pass the ViewHolder to toggle details
    }

    private fun toggleDetailsLayout(height: Int, viewHolder: DocumentationAdapter.DocumentationViewHolder) {
        val targetHeight = if (isExpanded) 139 else height
        val contentContainer = viewHolder.itemView.findViewById<View>(R.id.contentContainer)

        viewHolder.itemView.findViewById<TextView>(R.id.drop).visibility = if (isExpanded) View.VISIBLE else View.INVISIBLE
        viewHolder.itemView.findViewById<TextView>(R.id.up).visibility = if (isExpanded) View.INVISIBLE else View.VISIBLE

        if (!isExpanded) {
            contentContainer.visibility = View.VISIBLE
        }

        // Animate the height change
        val animator = ValueAnimator.ofInt(contentContainer.height, targetHeight).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Int
                contentContainer.layoutParams.height = animatedValue
                contentContainer.requestLayout()
            }
            start()
        }

        isExpanded = !isExpanded
    }
}
