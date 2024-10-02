package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.welearnjava.Adapter.LessonAdapter
import com.application.welearnjava.Model.Lessons
import com.application.welearnjava.databinding.FragmentLessonBinding

class LessonFragment : Fragment(), LessonAdapter.OnItemClickListener { // Implement the interface

    private lateinit var binding: FragmentLessonBinding
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonBinding.inflate(inflater, container, false)

        lessonAdapter = LessonAdapter(Lessons.lessons, this) // Pass `this` as the listener
        binding.lessonRecycleView.adapter = lessonAdapter
        binding.lessonRecycleView.layoutManager = LinearLayoutManager(requireContext())

        binding.quizBtn.setOnClickListener {
            findNavController().navigate(R.id.action_lessonFragment_to_quizFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_lessonFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onItemClick(lesson: Lessons) { // Override the onItemClick method
        val bundle = Bundle().apply {
            putString("Id", lesson.id.toString()) // Assuming Lessons implements Serializable
        }
        findNavController().navigate(R.id.action_lessonFragment_to_documentationFragment, bundle)
    }

}
