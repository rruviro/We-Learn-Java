package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentPreparationBinding

class PreparationFragment : Fragment() {

    private lateinit var binding: FragmentPreparationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreparationBinding.inflate(inflater, container, false)

        val Id = arguments?.getString("Id") ?: return binding.root
        val bundle = Bundle().apply {
            putString("Id", Id) // Assuming Lessons implements Serializable
        }

        binding.no.setOnClickListener {
            findNavController().navigate(R.id.action_preparationFragment_to_quizFragment)
        }

        binding.yes.setOnClickListener {
            findNavController().navigate(R.id.action_preparationFragment_to_chapterQueFragment, bundle)
        }

        return binding.root
    }

}