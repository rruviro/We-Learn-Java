package com.application.welearnjava.Model

data class Lessons(
    val id: Int,
    val title: String,
    val descript: String,
) {
    companion object {

        // Fixed data list inside the model
        val lessons = listOf(
            Lessons(1, "Introduction", "Overview of Java programming."),
            Lessons(2, "Java Basics", "Core concepts of Java syntax."),
            Lessons(3, "Data Types & Variables", "Types of data and variable declaration."),
            Lessons(4, "Conditional Statement", "Conditional statements and logic."),
            Lessons(5, "Loop", "Storing multiple values in a single variable."),
            Lessons(6, "Array", "Storing multiple values in a single variable."),
            Lessons(7, "Classes", "Blueprint for creating objects."),
            Lessons(8, "Constructor", "Special method for initializing objects."),
            Lessons(9, "Method", "Special method for initializing objects.")
        )

    }
}
