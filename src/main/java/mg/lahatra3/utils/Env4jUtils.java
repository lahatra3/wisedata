package mg.lahatra3.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Env4jUtils {

    private Env4jUtils() {}

    public static void load() {
        load(".env");
    }

    public static void load(String filename) {
        Path filenamePath = Paths.get(filename);
        if (!Files.exists(filenamePath)) {
            return;
        }
        try (Stream<String> fileContentStream = Files.lines(filenamePath)) {
            Map<String, String> envMap = fileContentStream
               .parallel()
               .filter(line -> !line.startsWith("#") && !line.trim().isBlank())
               .map(line -> line.split("=", 2))
               .collect(
                  Collectors.toMap(
                     part -> part[0],
                     part -> part[1]
                  )
               );
            setProperties(envMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return Optional.ofNullable(System.getenv(key))
           .or(() -> Optional.ofNullable(System.getProperty(key)))
           .orElseThrow(() -> new IllegalArgumentException("Missing required configuration: " + key + " ..."));
    }

    public static String get(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key))
           .or(() -> Optional.ofNullable(System.getProperty(key)))
           .orElse(defaultValue);
    }

    private static void setProperties(Map<String, String> envMap) {
        envMap.forEach(System::setProperty);
    }

}
