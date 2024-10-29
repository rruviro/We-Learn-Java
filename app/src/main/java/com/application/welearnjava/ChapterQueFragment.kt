package com.application.welearnjava

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.welearnjava.Model.ChapterQuestion
import com.application.welearnjava.Model.ChapterType
import com.application.welearnjava.Model.chapterQuestions
import com.application.welearnjava.databinding.FragmentChapterQueBinding

class ChapterQueFragment : Fragment() {

    private lateinit var binding: FragmentChapterQueBinding
    private var currentQuestion: ChapterQuestion? = null
    private var currentIndex = 0
    private var score = 0
    private var selectedAnswer: String = ""
    private var timeLeftInMillis: Long = 20000 // 20 seconds in milliseconds
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var randomQuestions: List<ChapterQuestion>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChapterQueBinding.inflate(inflater, container, false)

        val id = arguments?.getString("Id")
        if (id == null) {
            Toast.makeText(requireContext(), "Invalid Chapter ID", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return binding.root
        }

        // Load questions based on the given ID.
        randomQuestions = loadQuestionsById(id)

        if (randomQuestions.isEmpty()) {
            Toast.makeText(requireContext(), "No questions found.", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            loadQuestion(id)
            startTimer(id)
        }

        return binding.root
    }

    private fun loadQuestionsById(id: String): List<ChapterQuestion> {
        // Filter questions based on the provided ID.
        return chapterQuestions.filter { it.id == id.toInt() }.shuffled().take(20)
    }

    private fun startTimer(id: String) {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerUI()
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "Time's up!", Toast.LENGTH_SHORT).show()
                navigateToResultPage(score, id)
            }
        }.start()
    }

    private fun updateTimerUI() {
        val secondsLeft = (timeLeftInMillis / 1000).toInt()
        binding.timer.text = "Time left: $secondsLeft seconds"
    }

    private fun navigateToResultPage(totalScore: Int, id: String) {
        val bundle = Bundle().apply {
            putString("Id", id)
            putInt("totalScore", totalScore)
            putInt("totalQuestion", randomQuestions.size)
        }
        findNavController().navigate(R.id.action_chapterQueFragment_to_chapterResultFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }

    private fun updateProgress(currentIndex: Int, totalQuestions: Int) {
        val progressPercentage = ((currentIndex + 1).toFloat() / totalQuestions) * 100
        val targetWidth = (resources.displayMetrics.widthPixels * (progressPercentage / 100)).toInt()

        ValueAnimator.ofInt(binding.progressForeground.layoutParams.width, targetWidth).apply {
            duration = 700
            addUpdateListener {
                binding.progressForeground.layoutParams.width = it.animatedValue as Int
                binding.progressForeground.requestLayout()
            }
            start()
        }
    }

    private fun loadQuestion(id: String) {
        currentQuestion = randomQuestions[currentIndex]
        currentQuestion?.let { question ->
            binding.questionText.text = question.question
            binding.imageView.setImageResource(question.imageQuestion)
            updateProgress(currentIndex, randomQuestions.size)

            when (question.type) {
                ChapterType.MULTIPLE_CHOICE -> setupMultipleChoice(question, id)
                ChapterType.FILL_IN_THE_BLANK -> setupFillInTheBlank(question)
                ChapterType.TRUE_FALSE -> setupTrueFalse(question, id)
            }
        }
    }

    private fun setupTrueFalse(question: ChapterQuestion, id: String) {
        binding.multiple.visibility = View.GONE
        binding.truefalseContainer.visibility = View.VISIBLE
        binding.fillInTheBlankContainer.visibility = View.GONE
        binding.submitBtn.visibility = View.GONE

        binding.right.text = question.options?.getOrNull(0) ?: "True"
        binding.wrong.text = question.options?.getOrNull(1) ?: "False"

        binding.right.setOnClickListener { handleAnswer(binding.right.text.toString(), id) }
        binding.wrong.setOnClickListener { handleAnswer(binding.wrong.text.toString(), id) }
    }

    private fun setupMultipleChoice(question: ChapterQuestion, id: String) {
        binding.multiple.visibility = View.VISIBLE
        binding.truefalseContainer.visibility = View.GONE
        binding.fillInTheBlankContainer.visibility = View.GONE
        binding.submitBtn.visibility = View.GONE // Hide submit button for multiple choice

        val buttons = listOf(binding.button1, binding.button2, binding.button3, binding.button4)
        question.options?.forEachIndexed { index, option ->
            buttons[index].text = option
            buttons[index].setOnClickListener { handleAnswer(option, id) }
        }
    }

    private fun setupFillInTheBlank(question: ChapterQuestion) {
        binding.multiple.visibility = View.GONE
        binding.truefalseContainer.visibility = View.GONE
        binding.fillInTheBlankContainer.visibility = View.VISIBLE
        binding.submitBtn.visibility = View.VISIBLE // Show submit button for fill-in-the-blank

        val questionParts = question.question.split("_")
        binding.fillInTheBlankContainer.removeAllViews()

        questionParts.forEachIndexed { index, part ->
            binding.fillInTheBlankContainer.addView(TextView(requireContext()).apply {
                text = part
                textSize = 16f
            })

            if (index < question.answers.size) {
                binding.fillInTheBlankContainer.addView(EditText(requireContext()).apply {
                    id = View.generateViewId()
                    layoutParams = LinearLayout.LayoutParams(
                        150, LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        marginStart = 8
                        marginEnd = 8
                    }
                    inputType = InputType.TYPE_CLASS_TEXT
                    setBackgroundResource(R.drawable.edittext_stroke)
                })
            }
        }

        binding.submitBtn.setOnClickListener { checkFillInTheBlankAnswer(id.toString()) }
    }

    private fun checkFillInTheBlankAnswer(id: String) {
        val userAnswers = mutableListOf<String>()
        for (i in 0 until binding.fillInTheBlankContainer.childCount) {
            val view = binding.fillInTheBlankContainer.getChildAt(i)
            if (view is EditText) {
                userAnswers.add(view.text.toString().trim())
            }
        }

        if (userAnswers == currentQuestion?.answers) {
            score++
            Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Wrong answer.", Toast.LENGTH_SHORT).show()
        }
        loadNextQuestion(id)
    }

    private fun handleAnswer(selectedAnswer: String, id: String) {
        val selectedButton = when (selectedAnswer) {
            binding.right.text -> binding.right
            binding.wrong.text -> binding.wrong
            binding.button1.text -> binding.button1
            binding.button2.text -> binding.button2
            binding.button3.text -> binding.button3
            binding.button4.text -> binding.button4
            else -> null
        }

        if (currentQuestion?.answers?.contains(selectedAnswer) == true) {
            score++
            selectedButton?.let { changeButtonBackgroundColor(it, Color.rgb(57, 172, 96)) }
        } else {
            selectedButton?.let { changeButtonBackgroundColor(it, Color.rgb(233, 33, 69)) }
        }

        // Add a slight delay to allow the user to see the color change
        selectedButton?.postDelayed({
            loadNextQuestion(id)
        }, 700)
    }

    private fun loadNextQuestion(id: String) {
        currentIndex++
        if (currentIndex < randomQuestions.size) {
            loadQuestion(id)
        } else {
            navigateToResultPage(score, id)
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

}