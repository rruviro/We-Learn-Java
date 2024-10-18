package com.application.welearnjava

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.InputType
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.Model.DailyQuestion
import com.application.welearnjava.Model.QuestionType
import com.application.welearnjava.Model.questions
import com.application.welearnjava.databinding.FragmentChapterQueBinding

class ChapterQueFragment : Fragment() {

    private lateinit var binding: FragmentChapterQueBinding
    private var currentQuestion: DailyQuestion? = null
    private var currentIndex = 0
    private var score = 0
    private val randomQuestions = questions.shuffled().take(20)
    private var selectedAnswer: String = ""
    private var timeLeftInMillis: Long = 20000 // 30 seconds in milliseconds
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChapterQueBinding.inflate(inflater, container, false)
        loadQuestion()

        // Start the timer when the fragment is created
        startTimer()
        return binding.root
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerUI() // Method to update timer on screen
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "Time's up!", Toast.LENGTH_SHORT).show()
                navigateToResultPage(score) // Navigate to another fragment or result page
            }
        }.start()
    }

    private fun updateTimerUI() {
        val secondsLeft = (timeLeftInMillis / 1000).toInt()
        binding.timer.text = "Time left: $secondsLeft seconds"
    }

    private fun navigateToResultPage(totalScore: Int) {
        val bundle = Bundle().apply {
            putInt("totalScore", totalScore) // Use actual score
            putInt("totalQuestion", questions.size) // Use actual question count
        }
        findNavController().navigate(R.id.action_chapterQueFragment_to_chapterResultFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel() // Cancel the timer when the view is destroyed
    }

    private fun updateProgress(currentIndex: Int, totalQuestions: Int) {
        // Check to prevent division by zero
        if (totalQuestions <= 0) return

        // Calculate progress percentage
        val progressPercentage = ((currentIndex + 1).toFloat() / totalQuestions) * 100

        // Calculate the target width for the foreground view based on the percentage
        val targetWidth = (resources.displayMetrics.widthPixels * (progressPercentage / 100)).toInt()

        // Animate the width of the progress bar
        val layoutParams = binding.progressForeground.layoutParams
        val startWidth = layoutParams.width

        // Create a ValueAnimator to animate the width
        val animator = ValueAnimator.ofInt(startWidth, targetWidth)
        animator.addUpdateListener { valueAnimator ->
            layoutParams.width = valueAnimator.animatedValue as Int
            binding.progressForeground.layoutParams = layoutParams
        }

        // Set the duration of the animation (in milliseconds)
        animator.duration = 700 // Change this to adjust the speed of the animation
        animator.start()

    }

    private fun loadQuestion() {
        currentQuestion = randomQuestions[currentIndex]
        currentQuestion?.let { question ->
            binding.questionText.text = question.question
            binding.imageView.setImageResource(currentQuestion!!.imageQuestion)

            // Update progress bar
            updateProgress(currentIndex, randomQuestions.size)
            when (question.type) {
                QuestionType.MULTIPLE_CHOICE -> {
                    binding.multiple.visibility = View.VISIBLE
                    binding.truefalseContainer.visibility = View.GONE
                    binding.fillInTheBlankContainer.visibility = View.GONE
                    binding.submitBtn.visibility = View.GONE // Hide submit button for multiple choice
                    showMultipleChoiceButtons(question)
                }
                QuestionType.FILL_IN_THE_BLANK -> {
                    binding.multiple.visibility = View.GONE
                    binding.truefalseContainer.visibility = View.GONE
                    binding.fillInTheBlankContainer.visibility = View.VISIBLE
                    binding.submitBtn.visibility = View.VISIBLE // Show submit button for fill-in-the-blank
                    hideMultipleChoiceButtons()
                    setupFillInTheBlank(question)
                }
                QuestionType.TRUE_FALSE -> {
                    binding.multiple.visibility = View.GONE
                    binding.truefalseContainer.visibility = View.VISIBLE
                    binding.fillInTheBlankContainer.visibility = View.INVISIBLE
                    binding.submitBtn.visibility = View.GONE
                    showTwoChoiceButton(question)
                }
            }

        }
    }

    private fun showTwoChoiceButton(question: DailyQuestion) {
        // Show buttons for multiple choice
        binding.right.visibility = View.VISIBLE
        binding.wrong.visibility = View.VISIBLE

        // Set button text based on options
        binding.right.text = question.options?.get(0) ?: "Option 1"
        binding.wrong.text = question.options?.get(1) ?: "Option 2"

        // Set click listeners with automatic answer check and question load
        binding.right.setOnClickListener { handleButtonClick(binding.right, binding.right.text.toString()) }
        binding.wrong.setOnClickListener { handleButtonClick(binding.wrong, binding.wrong.text.toString()) }
    }

    private fun showMultipleChoiceButtons(question: DailyQuestion) {
        // Show buttons for multiple choice
        binding.button1.visibility = View.VISIBLE
        binding.button2.visibility = View.VISIBLE
        binding.button3.visibility = View.VISIBLE
        binding.button4.visibility = View.VISIBLE

        // Set button text based on options
        binding.button1.text = question.options?.get(0) ?: "Option 1"
        binding.button2.text = question.options?.get(1) ?: "Option 2"
        binding.button3.text = question.options?.get(2) ?: "Option 3"
        binding.button4.text = question.options?.get(3) ?: "Option 4"

        // Set click listeners with automatic answer check and question load
        binding.button1.setOnClickListener { handleButtonClick(binding.button1, binding.button1.text.toString()) }
        binding.button2.setOnClickListener { handleButtonClick(binding.button2, binding.button2.text.toString()) }
        binding.button3.setOnClickListener { handleButtonClick(binding.button3, binding.button3.text.toString()) }
        binding.button4.setOnClickListener { handleButtonClick(binding.button4, binding.button4.text.toString()) }
    }

    private fun handleButtonClick(view: View, answer: String) {
        selectedAnswer = answer
        checkAnswer(view)
        disableButtons()

        // Delay to show color animation, then load the next question
        Handler().postDelayed({
            currentIndex++
            if (currentIndex < randomQuestions.size) {
                resetButtonBackgrounds()
                loadQuestion()
                enableButtons()
            } else {
                navigateToResultPage(score)
            }
        }, 2000) // Delay for 2 seconds to show animation
    }

    private fun checkAnswer(view: View) {
        val isCorrect = currentQuestion?.answers?.contains(selectedAnswer) == true // For multiple answers
        if (isCorrect) {
            changeButtonBackgroundColor(view, Color.rgb(57, 172, 96)) // Green
            Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
            score++
        } else {
            changeButtonBackgroundColor(view, Color.rgb(233, 33, 69)) // Red
            Toast.makeText(requireContext(), "Wrong answer.", Toast.LENGTH_SHORT).show()
        }
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

    private fun resetButtonBackgrounds() {
        binding.button1.setBackgroundResource(R.drawable.buttons)
        binding.button2.setBackgroundResource(R.drawable.buttons)
        binding.button3.setBackgroundResource(R.drawable.buttons)
        binding.button4.setBackgroundResource(R.drawable.buttons)

        binding.right.setBackgroundResource(R.drawable.buttons)
        binding.wrong.setBackgroundResource(R.drawable.buttons)
    }

    private fun disableButtons() {
        binding.button1.isEnabled = false
        binding.button2.isEnabled = false
        binding.button3.isEnabled = false
        binding.button4.isEnabled = false

        binding.right.isEnabled = false
        binding.wrong.isEnabled = false
    }

    private fun enableButtons() {
        binding.button1.isEnabled = true
        binding.button2.isEnabled = true
        binding.button3.isEnabled = true
        binding.button4.isEnabled = true

        binding.right.isEnabled = true
        binding.wrong.isEnabled = true
    }

    private fun hideMultipleChoiceButtons() {
        binding.button1.visibility = View.GONE
        binding.button2.visibility = View.GONE
        binding.button3.visibility = View.GONE
        binding.button4.visibility = View.GONE

        binding.right.visibility = View.GONE
        binding.wrong.visibility = View.GONE
    }

    private fun setupFillInTheBlank(question: DailyQuestion) {
        val questionText = question.question
        val answersCount = question.answers.size

        binding.questionText.text = "Decision making"

        // Split the question text by blanks ('_____')
        val questionParts = questionText.split("_____")

        // Clear any previous dynamically created views in the container
        binding.fillInTheBlankContainer.removeAllViews()

        // Iterate over question parts and dynamically add text and blanks
        for (i in questionParts.indices) {
            val prefixTextView = TextView(requireContext()).apply {
                text = questionParts[i]
                textSize = 10f
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setTextColor(Color.BLACK)
            }
            binding.fillInTheBlankContainer.addView(prefixTextView)

            // Add an EditText if it's not the last part
            if (i < answersCount) {
                val answerEditText = EditText(requireContext()).apply {
                    id = View.generateViewId()
                    gravity = Gravity.CENTER
                    textSize = 10f
                    layoutParams = LinearLayout.LayoutParams(
                        150, // Width set to 0dp for weight distribution
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        weight = 1f // Allow EditText to take up remaining space
                        marginStart = 8 // Add margin for spacing
                        marginEnd = 8
                    }
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    setBackgroundResource(R.drawable.edittext_stroke)
                    inputType = InputType.TYPE_CLASS_TEXT
                }
                binding.fillInTheBlankContainer.addView(answerEditText)
            }
        }

        // Ensure the container is visible
        binding.fillInTheBlankContainer.visibility = View.VISIBLE

        // Set the click listener for the submit button
        binding.submitBtn.setOnClickListener {
            checkFillInTheBlankAnswer() // Call the method to check answers
        }
    }

    private fun checkFillInTheBlankAnswer() {
        val userAnswers = mutableListOf<String>()

        // Get all EditTexts from the container
        for (i in 0 until binding.fillInTheBlankContainer.childCount) {
            val view = binding.fillInTheBlankContainer.getChildAt(i)
            if (view is EditText) {
                userAnswers.add(view.text.toString().trim())
            }
        }

        // Get the correct answers
        val correctAnswers = currentQuestion?.answers ?: emptyList()

        // Iterate over user answers and check correctness
        for (i in userAnswers.indices) {
            val userAnswer = userAnswers[i]
            val correctAnswer = correctAnswers.getOrNull(i)

            if (userAnswer.equals(correctAnswer, true)) {
                // Correct answer, set to green
                setEditTextBackgroundColor(i, Color.rgb(57, 172, 96)) // Green
            } else {
                // Incorrect answer, set to red
                setEditTextBackgroundColor(i, Color.rgb(233, 33, 69)) // Red
            }
        }

        // Provide feedback based on the overall result
        if (userAnswers == correctAnswers) {
            Toast.makeText(requireContext(), "Correct! Moving to the next question.", Toast.LENGTH_SHORT).show()
            // Increment only if the answer is correct
            score++
        } else {
            Toast.makeText(requireContext(), "Incorrect. Try the next question.", Toast.LENGTH_SHORT).show()
        }

        // Delay for 2 seconds and then move to the next question
        Handler().postDelayed({
            currentIndex++
            if (currentIndex < randomQuestions.size) {
                resetButtonBackgrounds()
                loadQuestion()
                enableButtons()
            } else {
                navigateToResultPage(score)
            }
        }, 2000) // Delay for 2 seconds

    }

    // Helper method to set the background color of a specific EditText
    private fun setEditTextBackgroundColor(index: Int, color: Int) {
        val view = binding.fillInTheBlankContainer.getChildAt(index * 2 + 1) // Adjust index to get the correct EditText
        if (view is EditText) {
            view.setBackgroundColor(color)
        }
    }

}