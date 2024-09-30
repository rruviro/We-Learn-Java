package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lessonBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_lessonFragment)
        }

        binding.codebtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_codeFragment)
        }

        binding.referenceBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_referenceFragment)
        }

        return binding.root
    }

}