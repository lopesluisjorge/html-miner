package br.edu.ifma.utils;

import java.io.*;

public class FileUtils {

    public static String readFile(File file) {
        StringBuffer buffer = new StringBuffer();

        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            buffer.append(br.lines().reduce((acc, actual) -> acc.concat(actual)).get());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  buffer.toString();
    }

    public static File[] openFolder(String urlFolder) {
        File file = new File(urlFolder);
        return file.listFiles();
    }

}
