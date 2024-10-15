package com.application.welearnjava.Model

import com.application.welearnjava.R

data class ChapterQuestion(
    val exerciseId: Int, //primary key
    val id: Int, // foreign key
    val type: ChapterType,   // MULTIPLE_CHOICE, FILL_IN_THE_BLANK, or TRUE_FALSE
    val question: String,     // The actual question text
    val imageQuestion: Int,   // Image associated with the question, if any
    val options: List<String>?, // A list of options (only for multiple-choice questions)
    val answers: List<String>, // List of correct answers
    var selectedAnswers: MutableList<String>? = null // User's answers
)

enum class ChapterType {
    MULTIPLE_CHOICE,
    FILL_IN_THE_BLANK,
    TRUE_FALSE
}

// Updated list of questions
val chapterQuestions = listOf(

    ChapterQuestion(
        id = 1,
        exerciseId = 1,
        type = ChapterType.MULTIPLE_CHOICE,
        question = "What is the purpose of the main method in Java?",
        imageQuestion = 0,
        options = listOf("A) To define a class", "B) To execute the program", "C) To declare variables", "D) To comment code"),
        answers = listOf("B) To execute the program")
    ),

    ChapterQuestion(
        id = 1,
        exerciseId = 2,
        type = ChapterType.TRUE_FALSE,
        question = "What is a key feature of Java?",
        imageQuestion = 0,
        options = listOf("A) It is platform-dependent", "B) It uses a compiler only", "C) It is object-oriented", "D) It is a markup language"),
        answers = listOf("C) It is object-oriented") // Correct answer
    ),

    ChapterQuestion(
        id = 1,
        exerciseId = 3,
        type = ChapterType.TRUE_FALSE,
        question = "Does null have value?",
        imageQuestion = 0,
        options = listOf("True", "False"),
        answers = listOf("False")
    ),

    ChapterQuestion(
        id = 1,
        exerciseId = 4,
        type = ChapterType.FILL_IN_THE_BLANK,
        question = "Water freezes at _____ degrees Celsius.",
        imageQuestion = R.drawable._super, // Ensure this drawable exists
        options = null,
        answers = listOf("0")
    ),

)
