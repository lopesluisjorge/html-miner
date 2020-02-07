package br.edu.ifma.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static String readFile(File file) {
        if (file.isDirectory()) {
            return null;
        }
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

    public static void writeFile(String source, String target) {
        try {
            File folderSource = new File(source);
            File folderTarget = new File(target);
            if (!folderTarget.exists()) {
                folderTarget.mkdir();
            }
            // TODO: copiar todos os arquivos e pastas, recursivamente da pasta de source para a pasta target
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String dir, String filename, String content) {
        try  {
            File folder = new File(dir);
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(folder.getName() + "/" + filename);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File[] openFolder(String urlFolder) {
        File file = new File(urlFolder);
        return file.listFiles();
    }

}
