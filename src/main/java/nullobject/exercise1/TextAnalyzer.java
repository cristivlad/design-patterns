package nullobject.exercise1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextAnalyzer {
    private final Map<Character, Long> map;
    private static final char[] characterArray =
            "aeiouAEIOUbcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".toCharArray();

    public TextAnalyzer(String filename) throws IOException {
        Path path = Paths.get(filename);
        String contents = Files.readString(path);
        map = contents.chars()
                .filter(c -> Character.isLetter(c))
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> (char) c, Collectors.counting()));
    }

    public Map.Entry<Character, Long> getEntry(int n) {
        Objects.checkIndex(n, characterArray.length);
        char key = characterArray[n];
        return Map.entry(key,
                map.getOrDefault(key, 0L));
    }
}
