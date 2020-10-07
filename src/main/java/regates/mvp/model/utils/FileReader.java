package regates.mvp.model.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log
public class FileReader {

    /**
     * Read a file
     * @param path Absolute path of the file
     * @return Array of lines
     */
    public static String[] readFile(String path) {
        List<String> content = new ArrayList<>();

        try (Scanner myReader = new Scanner(new File(path))) {

            while (myReader.hasNextLine()) {
                content.add(myReader.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toArray(new String[0]);
    }
}
