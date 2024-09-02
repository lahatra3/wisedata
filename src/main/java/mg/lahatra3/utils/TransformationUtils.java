package mg.lahatra3.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class TransformationUtils {

   private TransformationUtils() {}

   public static Map<String, Map<String, String>> get() {
      ClassLoader classLoader = TransformationUtils.class.getClassLoader();
      String transformationFilenameConf = Objects.requireNonNull(
         classLoader.getResource("transformation.json")
      ).getFile();
      Path transformationFilenamePath = Paths.get(transformationFilenameConf);
      ObjectMapper objectMapper = new ObjectMapper();
      try {
         String tranformationConfString = Files.readString(transformationFilenamePath);
         return objectMapper.readValue(tranformationConfString, new TypeReference<>() {});
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

}
