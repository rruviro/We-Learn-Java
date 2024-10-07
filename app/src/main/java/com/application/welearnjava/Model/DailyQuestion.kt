package com.application.welearnjava.Model

import com.application.welearnjava.R

data class DailyQuestion(
    val type: QuestionType,   // MULTIPLE_CHOICE or FILL_IN_THE_BLANK
    val question: String,     // The actual question text with blanks
    val imageQuestion: Int,   // Image associated with the question, if any
    val options: List<String>?, // A list of options (only for multiple-choice questions)
    val answers: List<String>, // List of correct answers corresponding to blanks
    var selectedAnswers: MutableList<String>? = null // User's answers for each blank
)

enum class QuestionType {
    MULTIPLE_CHOICE,
    FILL_IN_THE_BLANK,
    TRUE_FALSE
}

val questions = listOf(

    DailyQuestion(
        QuestionType.MULTIPLE_CHOICE,
        "What is the capital of France?",
        0,
        listOf("Paris", "Berlin", "Rome", "Madrid"), // Options for the question
        listOf("Paris") // Correct answer as a list
    ),

    DailyQuestion(
        QuestionType.TRUE_FALSE,
        "Does String is used for Numbers?",
        0,
        listOf("True", "False"), // Options for the question
        listOf("True") // Correct answer as a list
    ),

    DailyQuestion(
        QuestionType.TRUE_FALSE,
        "Is null has value?",
        0,
        listOf("True", "False"), // Options for the question
        listOf("False") // Correct answer as a list
    ),

    DailyQuestion(
        QuestionType.MULTIPLE_CHOICE,
        "Which planet is known as the Red Planet?",
        0,
        listOf("Earth", "Mars", "Jupiter", "Venus"),
        listOf("Mars") // Correct answer as a list
    ),

    DailyQuestion(
        QuestionType.FILL_IN_THE_BLANK,
        "Water freezes at _____ degrees Celsius.",
        R.drawable._super,
        null, // No options for fill-in-the-blank questions
        listOf("0") // Correct answer as a list
    ),

    DailyQuestion(
        QuestionType.FILL_IN_THE_BLANK,
        "Water freezes at _____ degrees Celsius and _____ boils at _____ degrees",
        R.drawable._super,
        null, // No options for fill-in-the-blank questions
        listOf("0", "100", "200") // Correct answers as a list
    )

)
