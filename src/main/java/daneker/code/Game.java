package daneker.code;

import java.io.File;
import java.util.*;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static final File fileGetWordsToGuess = new File("C:\\Users\\Данекер\\Desktop\\daneker.txt");
    private static final File fileToWriteGuessedWords = new File("C:\\Users\\Данекер\\Desktop\\result.txt");
    private static final ReaderWriter readerWriter = new ReaderWriter();
    private static GameOver gameOver = new GameOver();

    private static int chooseLevel() {
        System.out.println("{GUESS THE WORD}");
        System.out.println("\n" +
                "[4] - all letters are closed\n" +
                "[3] - open one random letter\n" +
                "[2] - open two random letters\n" +
                "[1]- open three random letters");
        System.out.println("choose level:");
        int level = scanner.nextInt();
        return level;
    }

    public void start() throws Exception {
        List<String> guessed = readerWriter.getWordsFromFile(fileToWriteGuessedWords);
        List<String> toGuess = readerWriter.getWordsFromFile(fileGetWordsToGuess);
        game(guessed, toGuess, chooseLevel());
    }

    private static void game(List<String> guessedWords, List<String> wordsToGuess, int openedLetters) throws Exception {
        String wordToGuess = randomWord(wordsToGuess, guessedWords);
        readerWriter.writeGuessedWordToFile(wordToGuess, guessedWords, fileToWriteGuessedWords);
        char[] word = getFilledArray(openedLetters, wordToGuess);
        System.out.println("enter quantity of chances to guess word");
        int amountOfChances = scanner.nextInt();
        boolean win = input(amountOfChances, wordToGuess, word);
        if (!win) {
            gameOver.fillThird();
            System.out.println("The word was:" + wordToGuess);
        }
    }

    private static char[] getFilledArray(int n, String wordToGuess) {
        char[] arr = new char[wordToGuess.length()];
        Arrays.fill(arr, '*');
        int closedLetters = arr.length - n;
        int currentClosedLetters = 0;
        while (closedLetters != currentClosedLetters) {
            int index = new Random().nextInt(wordToGuess.length());
            arr[index] = wordToGuess.charAt(index);
            currentClosedLetters = countLetters(arr);
        }
        return arr;
    }

    private static int countLetters(char[] arr) {
        int counter = 0;
        for (char ch : arr) {
            if (Character.isLetter(ch)) {
                counter++;
            }
        }
        return counter;
    }


    private static boolean input(int chance, String wordToGuess, char[] arr) {
        System.out.println("be careful. You have only [" + chance + "] chances");
        System.out.println(String.valueOf(arr));
        while (chance > 0) {
            if(chance == wordToGuess.length() / 3){
                gameOver.fillSecond();
            } else if (chance == (wordToGuess.length() * 2) / 3) {
                gameOver.fillFirst();
            }
            if (String.valueOf(arr).indexOf("*") < 0) {
                won(wordToGuess, chance);
                return true;
            }
            System.out.print(">>>");
            String str = scanner.next();
            if (str.equals(wordToGuess)) {
                won(wordToGuess, chance);
                return true;
            }
            if (!wordToGuess.contains(str) && str.length() == 1)
                System.out.println("There is no such letter!");
            convertAndDisplay(arr, str.charAt(0), wordToGuess);
            System.out.println("chances:" + (chance - 1));
            chance--;
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
