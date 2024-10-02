package com.application.welearnjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private lateinit var binding: FragmentChallengeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}