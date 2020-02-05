package br.edu.ifma.mineracao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptureReferencesSection {

    public List<String> getReferenceSection(String string) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<span [a-zA-Z0-9=\":;# ]*>\\n[\\t]*Referências[ ]*\\n[\\t]*<\\/span>", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            list.add(matcher.group(0));
        }
        return list;
    }

}
