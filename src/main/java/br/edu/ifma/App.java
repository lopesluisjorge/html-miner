package br.edu.ifma;

import br.edu.ifma.mineracao.CaptureReferencesSection;
import br.edu.ifma.utils.FileUtils;
import br.edu.ifma.mineracao.CaptureQuotes;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class App {
    private App() {
    }

    public static void main(String[] args) {

        CaptureQuotes matches = new CaptureQuotes();

        MapString strings = new MapString();
        File[] wekas = FileUtils.openFolder("WEKA");
        for (File file : wekas) {
            strings.addToMap(file.getName(), FileUtils.readFile(file));
            System.out.println(file.getName() + " " + matches.getQuotesInPage(strings.get(file.getName())));
        }

        CaptureReferencesSection referencesSection = new CaptureReferencesSection();

        Collection<String> allFiles = strings.getAll().values();
        for (String str : allFiles) {
            System.out.println(referencesSection.getReferenceSection(str));
        }

        System.out.println(strings);
        List<String> filenames = strings.filenames();
        filenames.sort((a, b) -> a.compareTo(b));



        System.out.println(strings.filenames());
    }
}
