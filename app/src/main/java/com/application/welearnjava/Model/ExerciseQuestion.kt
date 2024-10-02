package com.application.welearnjava.Model

import com.application.welearnjava.R

data class ExerciseQuestion(
    val exerciseId: Int, //primary key
    val id: Int, // foreign key
    val question: String,
    val questionImg: Int,
    val choices: List<String>,
    val correctAnswer: String
) {
    companion object {
        val exeQuestions = listOf(
            ExerciseQuestion(
                id = 1,
                exerciseId = 1,
                question = "What is Java?",
                questionImg = 0,
                choices = listOf("a) A type of coffee", "b) A programming language", "c) A web browser", "d) A database system"),
                correctAnswer = "b) A programming language"
            ),
            ExerciseQuestion(
                id = 1,
                exerciseId = 2,
                question = "What is a key feature of Java?",
                questionImg = 0,
                choices = listOf("a) It is platform-dependent", "b) It uses a compiler only", "c) It is object-oriented", "d) It is a markup language"),
                correctAnswer = "c) It is object-oriented"
            ),

            ExerciseQuestion(
                id = 2,
                exerciseId = 1,
                question = "What is the purpose of the main method in Java?",
                questionImg = 0,
                choices = listOf("a) To define a class", "b) To execute the program", "c) To declare variables", "d) To comment code"),
                correctAnswer = "b) To execute the program"
            ),
            ExerciseQuestion(
                id = 2,
                exerciseId = 2,
                question = "Which symbol is used for single-line comments in Java?",
                questionImg = 0,
                choices = listOf("a) //", "b) /*", "c) #", "d) <!--"),
                correctAnswer = "a) //"
            ),

            ExerciseQuestion(
                id = 3,
                exerciseId = 1,
                question = "Which of the following is a primitive data type in Java?",
                questionImg = 0,
                choices = listOf("a) String", "b) Array", "c) int", "d) List"),
                correctAnswer = "c) int"
            ),
            ExerciseQuestion(
                id = 3,
                exerciseId = 2,
                question = "What type of variable is used to store a reference to an object in Java?",
                questionImg = 0,
                choices = listOf("a) Primitive variable", "b) Reference variable", "c) Constant variable", "d) Static variable"),
                correctAnswer = "b) Reference variable"
            ),

            ExerciseQuestion(
                id = 4,
                exerciseId = 1,
                question = "What will be the output of the following code?",
                questionImg = R.drawable.decision_exercise4,
                choices = listOf("a) 10", "b) Smaller", "c) Greater", "d) No output"),
                correctAnswer = "c) Greater"
            ),
            ExerciseQuestion(
                id = 4,
                exerciseId = 2,
                question = "Which statement is used to execute a block of code based on multiple conditions in Java?",
                questionImg = 0,
                choices = listOf("a) if statement", "b) switch statement", "c) for loop", "d) do-while loop"),
                correctAnswer = "b) switch statement"
            ),

            ExerciseQuestion(
                id = 5,
                exerciseId = 1,
                question = "Which loop guarantees that the code block will be executed at least once?",
                questionImg = 0,
                choices = listOf("a) For loop", "b) While loop", "c) Do-while loop", "d) Nested loop"),
                correctAnswer = " c) Do-while loop"
            ),
            ExerciseQuestion(
                id = 5,
                exerciseId = 2,
                question = "How many times will the following loop run?",
                questionImg = R.drawable.decision_exercise51,
                choices = listOf("a) 2", "b) 3", "c) 4", "d) Infinite"),
                correctAnswer = "b) 3"
            ),

            ExerciseQuestion(
                id = 6,
                exerciseId = 1,
                question = "What is an array in Java?",
                questionImg = 0,
                choices = listOf("a) A single variable holding multiple values", "b) A data structure to store a fixed-size sequential collection of elements of the same type", "c) A method to declare variables", "d) A type of loop"),
                correctAnswer = "b) A data structure to store a fixed-size sequential collection of elements of the same type"
            ),
            ExerciseQuestion(
                id = 6,
                exerciseId = 2,
                question = "How do you access the first element of an array named arr?",
                questionImg = 0,
                choices = listOf("a) arr[0]", "b) arr[1]", "c) arr.first()", "d) arr[begin]"),
                correctAnswer = "a) arr[0]"
            ),

            ExerciseQuestion(
                id = 7,
                exerciseId = 1,
                question = "What is a class in Java?",
                questionImg = 0,
                choices = listOf("a) A blueprint for creating objects", "b) A data type", "c) A method of storing data", "d) A type of loop"),
                correctAnswer = "a) A blueprint for creating objects"
            ),
            ExerciseQuestion(
                id = 7,
                exerciseId = 2,
                question = "What keyword is used to define a class in Java?",
                questionImg = 0,
                choices = listOf("a) define", "b) class", "c) object", "d) new"),
                correctAnswer = "b) class"
            ),

            ExerciseQuestion(
                id = 8,
                exerciseId = 1,
                question = "What is a constructor in Java?",
                questionImg = 0,
                choices = listOf("a) A special method to initialize objects", "b) A type of variable", "c) A method for deleting objects", "d) A loop for iteration"),
                correctAnswer = "a) A special method to initialize objects"
            ),
            ExerciseQuestion(
                id = 8,
                exerciseId = 2,
                question = "What is the primary difference between a constructor and a method?",
                questionImg = 0,
                choices = listOf("a) Constructors do not return a value", "b) Methods do not have parameters", "c) Constructors cannot be overloaded", "d) Methods are called automatically"),
                correctAnswer = "a) Constructors do not return a value"
            ),

            ExerciseQuestion(
                id = 9,
                exerciseId = 1,
                question = "What is a method in Java?",
                questionImg = 0,
                choices = listOf("a) A way to store data", "b) A block of code that performs a specific task", "c) A type of loop", "d) A way to declare variables"),
                correctAnswer = "b) A block of code that performs a specific task"
            ),
            ExerciseQuestion(
                id = 9,
                exerciseId = 2,
                question = "Which access modifier allows a method to be accessible from any other class?",
                questionImg = 0,
                choices = listOf("a) private", "b) protected", "c) public", "d) default"),
                correctAnswer = "c) public"
            ),

        )
    }
}
