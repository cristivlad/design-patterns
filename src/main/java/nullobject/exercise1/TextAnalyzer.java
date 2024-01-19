package nullobject.exercise1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TextAnalyzer {
    private final HashMap<Character, Long> map = new HashMap<>();
    private static final char[] characterArray =
            "aeiouAEIOUbcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".toCharArray();

    public TextAnalyzer(String filename) throws IOException {
        Path path = Paths.get(filename);
        String contents = Files.readString(path);
        for (String word : contents.split("\\PL+")) {
            char[] charArray = word.toCharArray();
            IntStream.rangeClosed(0, charArray.length - 1)
                    .forEach(n ->
                            map.compute(charArray[n],
                                    (k, v) -> v == null ? 1 : v + 1)
                    );
        }
    }

    public Map.Entry<Character, Long> getEntry(int n) {
        if (n < 0 || n > 51)
            throw new IllegalArgumentException();
        if (map.containsKey(characterArray[n])) {
            return Map.entry(characterArray[n],
                    map.get(characterArray[n]));
        }
        return null;
    }
}
