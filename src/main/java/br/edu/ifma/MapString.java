package br.edu.ifma;

import java.util.*;
import java.util.stream.Collectors;

public class MapString {

    private final Map<String, String> fileMap = new LinkedHashMap<>();

    public MapString addToMap(String key, String value) {
        fileMap.put(key, value);

        return  this;
    }

    public String get(String key) {
        return  fileMap.get(key);
    }

    public List<String> filenames() {
        return fileMap.keySet().stream().collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "MapString{" +
            "fileMap=" + fileMap +
            '}';
    }

    public Map<String, String> getAll() {
        return fileMap;
    }
}
