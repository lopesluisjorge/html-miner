package br.edu.ifma;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapQuotes {

    private final Map<String, Set<String>> fileMap = new LinkedHashMap<>();

    public MapQuotes addToMap(String key, Set<String> value) {
        fileMap.put(key, value);

        return this;
    }

    public Set<String> get(String key) {
        return fileMap.get(key);
    }

    public Set<String> keys() {
        return  fileMap.keySet();
    }

    public List<String> filenames() {
        return fileMap.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("MapString{ %s }", fileMap);
    }

    public Map<String, Set<String>> getAll() {
        return fileMap;
    }
}
