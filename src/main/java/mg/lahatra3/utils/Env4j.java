package mg.lahatra3.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Env4j {

    public void load() {
        load(".env");
    }

    public void load(String filename) {
        Path filenamePath = Paths.get(filename);
        try (Stream<String> fileContentStream = Files.lines(filenamePath)) {
            fileContentStream
                .parallel()
                .filter(line -> !line.startsWith("#") && !line.trim().isBlank())
                .map(line -> line.split("=", 2))
                .forEach(parts -> setProperties(parts[0], parts[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return Optional.ofNullable(System.getenv(key))
               .or(() -> Optional.ofNullable(System.getProperty(key)))
               .orElseThrow(() -> new IllegalArgumentException("Missing required configuration: " + key));
    }

    public String get(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key))
               .or(() -> Optional.ofNullable(System.getProperty(key)))
               .orElse(defaultValue);
    }

    private void setProperties(String key, String value) {
        System.setProperty(key, value);
    }
}
