package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.welearnjava.Adapter.ChapterAdapter
import com.application.welearnjava.Model.Chapter
import com.application.welearnjava.Model.Lessons
import com.application.welearnjava.databinding.FragmentQuizBinding

class QuizFragment : Fragment(), ChapterAdapter.OnItemClickListener  {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var chapterAdapter: ChapterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuizBinding.inflate(inflater, container, false)

        chapterAdapter = ChapterAdapter(Chapter.chapters, this)
        binding.chapterRecycleView.adapter = chapterAdapter
        binding.chapterRecycleView.layoutManager = LinearLayoutManager(requireContext())

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_lessonFragment)
        }

        return binding.root

    }

    override fun onItemClick(chapters: Chapter) { // Override the onItemClick method
        val bundle = Bundle().apply {
            putString("Id", chapters.id.toString()) // Assuming Lessons implements Serializable
        }
        findNavController().navigate(R.id.action_quizFragment_to_preparationFragment, bundle)
    }

}