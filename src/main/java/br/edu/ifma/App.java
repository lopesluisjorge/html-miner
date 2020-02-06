package br.edu.ifma;

import br.edu.ifma.mineracao.CaptureReferencesSection;
import br.edu.ifma.utils.FileUtils;
import br.edu.ifma.mineracao.CaptureQuotes;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        String openDir = "WEKA";

        CaptureQuotes matches = new CaptureQuotes();
        CaptureReferencesSection referencesSection = new CaptureReferencesSection();

        String referencePage = null;

        Map<String, String> completeFiles = new HashMap<>();
        MapQuotes strings = new MapQuotes();

        File[] wekas = FileUtils.openFolder(openDir);
        for (File file : wekas) {
            String fileContents = FileUtils.readFile(file);
            completeFiles.put(file.getName(), fileContents);
            List<String> quotes = matches.getQuotesInPage(fileContents);
            if (!quotes.isEmpty()) {
                strings.addToMap(file.getName(), quotes.stream().collect(Collectors.toSet()));
            }
            if (!referencesSection.getReferenceSection(fileContents).isEmpty()) {
                referencePage = file.getName();
            }
        }

        System.out.println("Citações: " + strings);
        System.out.println("Página de Referência: " + referencePage);

        String referenceCompleteFile = completeFiles.get(referencePage);

        completeFiles.put(referencePage, referenceCompleteFile.replace("<span ", "<span class=\"referencePage\" "));

        System.out.println(completeFiles.get(referencePage));

        Set<String> quoteKeysPage = strings.keys();

        System.out.println(quoteKeysPage);

        for (String page : quoteKeysPage) {
            Set<String> quotesPage = strings.get(page);
            for (String quotes : quotesPage) {
                completeFiles.put(page, completeFiles.get(page).replace(quotes, "<a href=\""+ referencePage + "#referencePage" +"\">" + quotes + "</a>"));
            }
        }

        Set<String> filenames = completeFiles.keySet();
        for (String filename : filenames) {
            FileUtils.writeFile(openDir + "_2", filename, completeFiles.get(filename));
        }

        //System.out.println(strings);
        //List<String> filenames = strings.filenames();
        //filenames.sort((a, b) -> a.compareTo(b));



        //System.out.println(strings.filenames());
    }
}
