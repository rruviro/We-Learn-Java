package com.application.welearnjava.Model

import com.application.welearnjava.R

data class Documentation(
    val id: Int,
    val height: Int,
    val topic: String,
    val image: Int,
    val description: String,
) {
    companion object {

        val documentation = listOf(

            Documentation(1, 300, "What is Java?", 0,"Java is a high-level, object-oriented programming language designed for portability across platforms. It allows developers to write code that can run on any device equipped with a Java Virtual Machine (JVM), making it a popular choice for web applications, mobile applications, and enterprise software."),
            Documentation(1, 420,"Java Basics", 0, "1991: Java was initiated by James Gosling and his team at Sun Microsystems. Originally called \"Oak,\" it was designed for embedded systems.1995: The name was changed to Java, and it was officially released as a programming language for building web applications.1996: Sun released the first version of the Java Development Kit (JDK).2006: Java was made open-source with the release of the Java Platform, Standard Edition (Java SE).2010: Oracle Corporation acquired Sun Microsystems and became the steward of Java." +
                    "2021: Java 17 was released as a long-term support version, continuing to evolve with features like pattern matching and sealed classes."),
            Documentation(1, 450,"Data Types & Variables", 0,"Platform Independence: Write once, run anywhere (WORA) capability through the JVM.Object-Oriented: Supports encapsulation, inheritance, and polymorphism.Robustness: Strong memory management and exception handling features." +
                    "Security: Built-in security features, such as bytecode verification and the sandboxing of applets.Multithreading: Supports concurrent programming with built-in thread management.Rich API: Extensive standard libraries for networking, I/O operations, and data structures.Automatic Memory Management: Through garbage collection, Java handles memory allocation and deallocation automatically."),

            Documentation(2, 270, "Syntax", 0,"Java syntax is the set of rules that defines the combinations of symbols that are considered to be correctly structured programs. Key elements include classes, methods, statements, and expressions."),
            Documentation(2, 460,"Hello World", R.drawable.hello, "The traditional first program in Java is the \"Hello, World!\" program, which prints the phrase to the console."),
            Documentation(2, 300,"Comments", 0,"Comments in Java are non-executable parts of the code used for explanation.\n\nThey can be single-line or multi-line:\n•Single-line: // This is a single-line comment\n•Multi-line: /* This is a multi-line comment */"),
            Documentation(2, 300, "Keywords", 0,"Keywords are reserved words in Java that have a predefined meaning.\n\nExamples\n include class, public, static, void, if, else, and for."),
            Documentation(2, 370,"Naming Conventions", 0, "Naming Conventions\n\nJava follows specific naming conventions to enhance code readability:\n•Class names: Use CamelCase (e.g., MyClass).\n•Method names: Use camelCase (e.g., myMethod).\n•Constants: Use ALL_CAPS with underscores (e.g., MAX_VALUE)."),
            Documentation(2, 650,"User Input", R.drawable.userinput,"User input can be obtained using the Scanner class:"),

            Documentation(3, 270, "Data Types", 0,"Java has two main categories of data types:\n•Primitive Data Types: Include int, char, float, double, boolean, byte, short, and long.\n•Non-Primitive Data Types: Include Strings, Arrays, Classes, and Interfaces.\n"),
            Documentation(3, 270, "Reference Data Types", 0,"Reference data types refer to objects and are created using classes. Examples include String, Arrays, and user-defined classes."),
            Documentation(3, 340, "Variables", R.drawable.datatype,"Variables are used to store data. They must be declared with a data type before use:"),

            Documentation(4, 340, "If Statement", R.drawable._if,"The if statement executes a block of code if a specified condition is true."),
            Documentation(4, 450, "If..Else Statement", R.drawable._else,"The if..else statement allows execution of one block if the condition is true and another block if it is false."),
            Documentation(4, 440, "If..Else..If Ladder", R.drawable._elseif,"An if..else..if ladder is used for multiple conditions."),
            Documentation(4, 490, "Nested If..Else Statement", R.drawable.nested,"Nested if..else statements are used to check multiple conditions."),
            Documentation(4, 490, "Switch", R.drawable._switch,"The switch statement allows testing a variable against multiple values."),

            Documentation(5, 340, "While Loop", R.drawable._while,"A while loop executes a block of code as long as the specified condition is true."),
            Documentation(5, 450, "Do While Loop", R.drawable._do,"A do while loop executes the code block once, and then repeats as long as the condition is true."),
            Documentation(5, 440, "For Loop", R.drawable._loop,"A for loop is used for iterating over a range of values"),

            Documentation(6, 230, "What is an Array", 0, "An array is a collection of elements of the same data type, stored in contiguous memory locations."),
            Documentation(6, 300, "Creating an Array", R.drawable._creating, "Arrays can be declared and created as follows:"),
            Documentation(6, 300, "Initializing an Array", R.drawable._initializing, "Arrays can be initialized with values during declaration:"),
            Documentation(6, 300, "Array Length", R.drawable._array, "The length of an array can be obtained using the length property:"),
            Documentation(6, 300, "Accessing an Array", R.drawable._access, "Elements of an array can be accessed using their index:"),
            Documentation(6, 380, "Iterating Array", R.drawable._iterating, "Arrays can be iterated using loops:"),
            Documentation(6, 400, "Array Example", R.drawable._example, ""),
            Documentation(6, 320, "Multi-Dimensional Array", R.drawable._multiple, "A multi-dimensional array is an array of arrays. For example, a two-dimensional array can be declared as follows:"),

            Documentation(7, 230, "What is Class in Java", 0,"A class is a blueprint for creating objects. It defines properties (attributes) and methods (functions) that an object can have."),
            Documentation(7, 370, "Defining a Class in Java", R.drawable._define,"A class is defined using the class keyword:"),
            Documentation(7, 440, "Classes with Methods", R.drawable._class,"Methods can be defined within a class to perform specific actions:"),
            Documentation(7, 300, "Classes and Objects", R.drawable._object,"An object is an instance of a class. It is created using the new keyword:"),

            Documentation(8, 230, "What is Constructor", 0,"A constructor is a special method that is called when an object is instantiated. It initializes the object's properties."),
            Documentation(8, 550, "Parameterized Constructor in Java", R.drawable._parameter,"Difference Between Constructor and Method\nA constructor has the same name as the class and does not have a return type.\n•A method can have any name and must have a return type."),

            Documentation(9, 230, "What are Methods in Java", 0, "Methods are blocks of code that perform a specific task and can be called multiple times."),
            Documentation(9, 230, "Method Access Modifiers", 0, "Methods can have access modifiers such as\npublic, private, protected, and default (package-private)."),
            Documentation(9, 230, "Method Return Types", 0, "Methods can return a value of a specific type. If no value is returned, the return type is void."),
            Documentation(9, 470, "Multiple Return Statement", R.drawable._return, "A method can have multiple return statements to exit and return a value:"),
            Documentation(9, 300, "Method Calling", R.drawable._method, "Methods can be called using the object reference:"),
            Documentation(9, 730, "Super Keyword", R.drawable._super, "The super keyword is used to access members of the parent class, such as constructors and methods."),

        )

    }
}