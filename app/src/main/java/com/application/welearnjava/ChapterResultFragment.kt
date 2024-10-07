package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.Model.DailyQuestion
import com.application.welearnjava.Model.questions
import com.application.welearnjava.databinding.FragmentChapterResultBinding

class ChapterResultFragment : Fragment() {

    private lateinit var binding: FragmentChapterResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChapterResultBinding.inflate(inflater, container, false)

        // Get the total score and total question from arguments
        val totalScore = arguments?.getInt("totalScore") ?: 0
        val totalQuestion = arguments?.getInt("totalQuestion") ?: 0

        // Calculate the percentage
        val percentage = if (totalQuestion > 0) {
            (totalScore.toDouble() / totalQuestion * 100).toInt() // Convert to Int if needed
        } else {
            0 // Handle the case where totalQuestion is 0 to avoid division by zero
        }

        val mistake = totalQuestion - totalScore
        binding.percentage.text = "$percentage%"
        binding.totalCorrect.text = totalScore.toString()
        binding.totalQue.text = totalQuestion.toString()
        binding.totalMistake.text = mistake.toString()

        binding.retake.setOnClickListener {
            findNavController().navigate(R.id.action_chapterResultFragment_to_preparationFragment)
        }

        binding.quiz.setOnClickListener {
            findNavController().navigate(R.id.action_chapterResultFragment_to_quizFragment)
        }

        binding.home.setOnClickListener {
            findNavController().navigate(R.id.action_chapterResultFragment_to_homeFragment)
        }

        return binding.root
    }


}