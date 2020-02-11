package br.edu.ifma;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.edu.ifma.mineracao.CaptureQuotes;
import br.edu.ifma.mineracao.CaptureReferencesSection;
import br.edu.ifma.utils.FilesUtils;

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

        File[] wekas = FilesUtils.openFolder(openDir);

        for (File file : wekas) {
            String fileContents = FilesUtils.readFile(file);
            if (Objects.isNull(fileContents)) {
                continue;
            }
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
                completeFiles.put(page, completeFiles.get(page).replace(quotes,
                        "<a href=\"" + referencePage + "#referencePage" + "\">" + quotes + "</a>"));
            }
        }
        
        FilesUtils.copyFiles(openDir, openDir + "_2");

        Set<String> filenames = completeFiles.keySet();
        for (String filename : filenames) {
            FilesUtils.writeFile(openDir + "_2", filename, completeFiles.get(filename));
        }
    }

}
