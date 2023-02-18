# Guess-the-word-Java-console-game

Firstly you have to create two txt.files.
The first file will store all the words and the second will store the already played words so that the words do not repeat.
Please don't forget to change the file path to the [Game] class.
![photo_2023-02-18_12-05-07](https://user-images.githubusercontent.com/113981956/219844152-e028d9da-132e-4b43-b538-846242ff0a08.jpg)
# Example:
![Screenshot (25)](https://user-images.githubusercontent.com/113981956/219843921-31578066-7d1a-4996-bf03-169c2110714f.png)




1 level
Read the dictionary file and choose a random word.
Make the computer display this word, closed with asterisks, and offer to guess the letter, after each guessed letter, the guessed letters are substituted for the asterisks.

Sample output to the screen (word - whiskey):

*******
> s
***s***
>b
There is no such letter!
***s***
> e
***s*e*

2 level
Add a win condition and a move counter to the program.

Screen output example:

*randy
>b
you win! The word was "brandy". You won't in 7 tries.

3 level
Add the ability to enter the whole word instead of a single letter. If the word is correct, show the information and the counter of moves on the screen.

b**r
> beer
you win! The word was "beer". You won't in 3 tries.

4th level
Add an attempt counter that will decrease each time you try to guess a letter or a whole word. If the attempt counter reaches 0, the user loses.

d***ing
>y
No, there is no such letter in this word. Your number of tries left: 0
game over! The word was "dancing"


5th level
Add the ability to save already played words to a file, and when starting a new game, 
choose a guaranteed new word. For example, if the word hello has already been played (with any result), 
it will no longer be selected the next time the program is run.
