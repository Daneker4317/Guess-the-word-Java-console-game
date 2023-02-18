package daneker.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderWriter {
   public  void writeGuessedWordToFile(String word, List<String> pastWords  , File fileToWriteGuessedWords) throws Exception {
        FileWriter fileWriter = new FileWriter(fileToWriteGuessedWords);
        fileWriter.write(word);
        pastWords.forEach(daneker -> {
            try {
                fileWriter.append("\n");
                fileWriter.write(daneker);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileWriter.close();
    }
    public  List<String> getWordsFromFile(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        List<String> words = new ArrayList<>();
        while (in.hasNext())
            words.add(in.nextLine());
        return words;
    }
}
