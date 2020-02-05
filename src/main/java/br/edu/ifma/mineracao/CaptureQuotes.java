package br.edu.ifma.mineracao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptureQuotes {

    // [string ano];
    public List<String> getQuotesInPage(String string) {
        List<String> list = new ArrayList<>();
        int i = 0;
        Pattern pattern = Pattern.compile("\\[[a-zA-Z&;. ]+ [0-9]{4}\\]");
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            list.add(matcher.group(i));
        }
        return list;
    }

}
