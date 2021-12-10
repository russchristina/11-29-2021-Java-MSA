> The goal of a coding challenge is to practice using the concepts you've learned about over the past couple of weeks. Though I am not "grading" this challenge as you are not students, I will be looking at your attempts, which should be pushed to the "Coding Challenge" directory on your Git branch. Please note that your answers don't have to be correct and that copying and pasting an answer from elsewhere does not help you as you will be required to solve these types of problems in front of an interviewer one day. In essence, if you cheat, you only cheat yourself.

# Instructions

1. Each challenge's solution should be contained within its own file within a Java project you have created. In other words, if there are 3 challenges, you should complete each challenge within its own file within one Java project.
2. Each solution should contain a Java comment which explains the steps you would take to solve the challenge if you were NOT writing Java code. In other words, detail your thought process for solving the problem in general as coding challenges are not about the code itself but about using a syntax to apply a solution to a real-world problem. In fact, it is helpful to detail the steps before you start writing the source code which solves the problem.
3. Each solution should be tested with JUnit 5. You should write AT LEAST 3 tests per solution.
4. Your attempts should be pushed to the "Coding Challenge" directory on your Git branch. I will not assist with you this as this is a part of the challenge as you should be aiming to become more comfortable with Git. If you can't remember how to use a certain Git command, please refer to the recording from week one in which we discussed Git.

**NOTE**: If you wish to challenge yourself, you can write your solutions without the assistance of an IDE and then open the code in your IDE later. This is a great way of practicing your syntax as you are required to know the syntax of any programming language you're learning for a job interview.

## Challenge 1: Pyramid

Write a method that prints a left-aligned pyramid with n rows, where n is an integer that is passed to the method. Each row of your pyramid should contain a number of asterisks that is equal to the row number.

For example, if the number 7 is passed to the method, it should print:

\* \
\*\* \
\*\*\* \
\*\*\*\* \
\*\*\*\*\* \
\*\*\*\*\*\* \
\*\*\*\*\*\*\* 

## Challenge 2: Fibonacci

Write a method that calculates the first n numbers in the Fibonacci sequence, where n is an integer that is passed to the method. 

NOTE: The Fibonacci sequence is a sequence in which the next number in the sequence is calculated by adding the previous two numbers in the sequence. For example, when n is 7, the method should return 13. The logic is as follows:

1 1 2 3 5 8 13 -> 1 + 1 = 2, 2 + 1 = 3, 3 + 2 = 5, 5 + 3 = 8, 8 + 5 = 13

## Challenge 3: Palindrome

Write a method that returns true if a String that is passed into the method is a palindrome and false if the String is not a palindrome. Assume that the String is just a single word with no spaces.

NOTE: A palindrome is simply a word that is spelled the same forwards and backwards. Examples include: 

civic
radar
madam
refer
aibohphobia

CHALLENGE: For a slightly more challenging variation of the method, assume that the String can contain spaces. For example:

taco cat
was it a car or a cat I saw
