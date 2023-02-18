package daneker.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static final File fileGetWordsToGuess = new File("C:\\Users\\Данекер\\Desktop\\daneker.txt");
    private static final File fileToWriteGuessedWords = new File("C:\\Users\\Данекер\\Desktop\\result.txt");
    private static final ReaderWriter readerWriter = new ReaderWriter();

    public void start() throws Exception {
        List<String> guessed = readerWriter.getWordsFromFile(fileToWriteGuessedWords);
        List<String> toGuess =readerWriter.getWordsFromFile(fileGetWordsToGuess);
        game(guessed, toGuess);
    }

    private static void game(List<String> guessedWords, List<String> wordsToGuess) throws Exception {
        String wordToGuess = randomWord(wordsToGuess, guessedWords);
        readerWriter.writeGuessedWordToFile(wordToGuess, guessedWords , fileToWriteGuessedWords);
        char[] word = new char[wordToGuess.length()];
        Arrays.fill(word, '*');
        System.out.println("enter quantity of chances to guess word");
        int amountOfChances = scanner.nextInt();
        boolean win = input(amountOfChances, wordToGuess, word);
        if(!win)
            System.out.println("Game over! The word was:" + wordToGuess);
    }

    private static boolean input(int c, String wordToGuess, char[] arr) {
        System.out.println("be careful. You have only [" + c + "] chances");
        System.out.println(String.valueOf(arr));
        while (c > 0) {
            if (String.valueOf(arr).indexOf("*") < 0) {
                won(wordToGuess, c);
                return true;
            }
            System.out.print(">>>");
            String str = scanner.next();
            if (str.equals(wordToGuess)) {
                won(wordToGuess, c);
                return true;
            }
            if (!wordToGuess.contains(str) && str.length() == 1)
                System.out.println("There is no such letter!");
            convertAndDisplay(arr, str.charAt(0), wordToGuess);
            System.out.println("chances:" + (c - 1));
            c--;
        }
        return false;
    }

    private static void won(String word, int c) {
        System.out.println("You win! The word was{" + word + "}. You won in " + (c - 1) + " tries.");
    }

    private static void convertAndDisplay(char arr[], char letter, String wordToGuess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter)
                arr[i] = letter;
        }
        System.out.println(String.valueOf(arr));
    }

    private static String randomWord(List<String> words, List<String> pastWords) {
        List<String> list = new ArrayList<>();
        words.stream().filter(word -> !pastWords.contains(word)).forEach(word -> list.add(word));
        return list.get(new Random().nextInt(list.size()));
    }


}
