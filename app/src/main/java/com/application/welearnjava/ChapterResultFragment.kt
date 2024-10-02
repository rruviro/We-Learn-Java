package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentChapterResultBinding

class ChapterResultFragment : Fragment() {

    private lateinit var binding: FragmentChapterResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChapterResultBinding.inflate(inflater, container, false)

        binding.quiz.setOnClickListener {
            findNavController().navigate(R.id.action_chapterResultFragment_to_quizFragment)
        }

        binding.home.setOnClickListener {
            findNavController().navigate(R.id.action_chapterResultFragment_to_homeFragment)
        }

        return binding.root
    }

}