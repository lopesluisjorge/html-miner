package br.edu.ifma.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String readFile(File file) {
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            return br.lines().reduce((acc, actual) -> acc.concat(actual)).get();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File[] openFolder(String urlFolder) {
        File file = new File(urlFolder);
        return file.listFiles();
    }

}
