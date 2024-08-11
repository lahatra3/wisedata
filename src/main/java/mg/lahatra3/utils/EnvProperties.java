package mg.lahatra3.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EnvProperties {

    public static void load() {
        load(".env");
    }

    public static void load(String filename) {
        Path filenamePath = Paths.get(filename);
        try (Stream<String> fileContentStream = Files.lines(filenamePath)) {
            fileContentStream
                .parallel()
                .filter(line -> !line.startsWith("#"))
                .map(line -> line.split("=", 2))
                .forEach(parts -> setProperties(parts[0], parts[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setProperties(String key, String value) {
        System.setProperty(key, value);
    }
}
