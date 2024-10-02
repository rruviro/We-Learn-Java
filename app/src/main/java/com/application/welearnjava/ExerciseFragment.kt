package com.application.welearnjava

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.Model.ExerciseQuestion
import com.application.welearnjava.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private var score = 0
    private var currentQuestionIndex = 0
    private var selectedAnswer: String = ""
    private lateinit var currentQuestions: List<ExerciseQuestion>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)

        // Get the selected exerciseId from arguments
        val selectedExerciseId = arguments?.getString("Id") ?: return binding.root
        val IdConverted = selectedExerciseId.toInt()

        // Load questions based on the selected exerciseId
        currentQuestions = ExerciseQuestion.exeQuestions.filter { it.id == IdConverted }

        binding.back.setOnClickListener { findNavController().popBackStack() }
        binding.button1.setOnClickListener { handleButtonClick(it) }
        binding.button2.setOnClickListener { handleButtonClick(it) }
        binding.button3.setOnClickListener { handleButtonClick(it) }
        binding.button4.setOnClickListener { handleButtonClick(it) }

        loadNewQuestion()
        return binding.root
    }

    private fun changeButtonBackgroundColor(view: View, color: Int) {
        val drawable = GradientDrawable().apply {
            cornerRadius = 10f
            setColor(Color.TRANSPARENT)
        }
        view.background = drawable
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 700
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                val fillColor = blendColors(Color.rgb(202, 173, 24), color, progress)
                drawable.setColor(fillColor)
            }
            start()
        }
    }

    private fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
        val inverseRatio = 1 - ratio
        val r = (Color.red(color1) * inverseRatio + Color.red(color2) * ratio).toInt()
        val g = (Color.green(color1) * inverseRatio + Color.green(color2) * ratio).toInt()
        val b = (Color.blue(color1) * inverseRatio + Color.blue(color2) * ratio).toInt()
        return Color.rgb(r, g, b)
    }

    private fun handleButtonClick(view: View) {
        disableButtons()
        val clickedButton = view as TextView
        selectedAnswer = clickedButton.text.toString()
        if (selectedAnswer == currentQuestions[currentQuestionIndex].correctAnswer) {
            score++
            changeCorrectAnswerBackground()
        } else {
            changeButtonBackgroundColor(clickedButton, Color.rgb(233, 33, 69))
        }
        Handler().postDelayed({
            resetButtonBackgrounds()
            currentQuestionIndex++
            loadNewQuestion()
            enableButtons()
        }, 2000)
    }

    private fun loadNewQuestion() {
        if (currentQuestionIndex >= currentQuestions.size) {
            finishQuiz()
            return
        }
        val currentQuestion = currentQuestions[currentQuestionIndex]
        binding.imageView.setImageResource(currentQuestion.questionImg)
        binding.questionTitle.text = currentQuestion.question
        binding.button1.text = currentQuestion.choices[0]
        binding.button2.text = currentQuestion.choices[1]
        binding.button3.text = currentQuestion.choices[2]
        binding.button4.text = currentQuestion.choices[3]
    }

    private fun finishQuiz() {
        val passStatus = if (score > currentQuestions.size * 0.60) "Passed" else "Failed"

        // Inflate custom layout
        val dialogView = layoutInflater.inflate(R.layout.dialog_exercise_status, null)

        // Set dialog title and message dynamically
        dialogView.findViewById<TextView>(R.id.dialogTitle).text = passStatus
        dialogView.findViewById<TextView>(R.id.dialogMessage).text = "Score is $score out of ${currentQuestions.size}"

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)  // Use custom view
            .setPositiveButton("Restart") { _, _ -> restartQuiz() }
            .setNegativeButton("Quiz") { _, _ -> findNavController().navigate(R.id.action_exerciseFragment_to_quizFragment) }
            .setCancelable(false)
            .create()

        dialog.show()

        // Adjust dialog width
        val widthInDp = 300  // Example width in dp
        val widthInPx = (widthInDp * resources.displayMetrics.density).toInt()

        dialog.window?.setLayout(
            widthInPx,  // Custom width in pixels
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Change the background color of the entire dialog window
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))  // Set custom background color

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.rgb(57, 172, 96))  // Change Restart button color
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.rgb(57, 172, 96)) // Change Quiz button color

    }


    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }

    private fun resetButtonBackgrounds() {
        binding.button1.setBackgroundResource(R.drawable.buttons)
        binding.button2.setBackgroundResource(R.drawable.buttons)
        binding.button3.setBackgroundResource(R.drawable.buttons)
        binding.button4.setBackgroundResource(R.drawable.buttons)
    }

    private fun changeCorrectAnswerBackground() {
        val correctAnswer = currentQuestions[currentQuestionIndex].correctAnswer
        when (correctAnswer) {
            binding.button1.text.toString() -> changeButtonBackgroundColor(binding.button1, Color.rgb(57, 172, 96))
            binding.button2.text.toString() -> changeButtonBackgroundColor(binding.button2, Color.rgb(57, 172, 96))
            binding.button3.text.toString() -> changeButtonBackgroundColor(binding.button3, Color.rgb(57, 172, 96))
            binding.button4.text.toString() -> changeButtonBackgroundColor(binding.button4, Color.rgb(57, 172, 96))
        }
    }

    private fun disableButtons() {
        binding.button1.isEnabled = false
        binding.button2.isEnabled = false
        binding.button3.isEnabled = false
        binding.button4.isEnabled = false
    }

    private fun enableButtons() {
        binding.button1.isEnabled = true
        binding.button2.isEnabled = true
        binding.button3.isEnabled = true
        binding.button4.isEnabled = true
    }
}
