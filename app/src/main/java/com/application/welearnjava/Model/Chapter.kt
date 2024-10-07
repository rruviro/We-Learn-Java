package com.application.welearnjava.Model

data class Chapter(
    val id: Int,
    val title: String,
    val descript: String,
) {
    companion object {
        val chapters = listOf(
            Chapter(1, "Chapter 1", "Lesson 1 & Lesson 2"),
            Chapter(2, "Chapter 2", "Lesson 3 & Lesson 4"),
            Chapter(3, "Chapter 3", "Lesson 5 & Lesson 6"),
            Chapter(4, "Chapter 4", "Lesson 7, Lesson 8 & Lesson 9"),
            Chapter(5, "Chapter 5", "Lesson 1 - 9"),
        )
    }
}