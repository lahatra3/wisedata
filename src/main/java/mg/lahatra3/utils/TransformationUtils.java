package mg.lahatra3.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class TransformationUtils {

   private TransformationUtils() {}

   public static Map<String, Map<String, String>> get() {
      ClassLoader classLoader = TransformationUtils.class.getClassLoader();
      try(InputStream inputStream = classLoader.getResourceAsStream("transformation.json")) {
         if (Objects.isNull(inputStream)) {
            throw new RuntimeException("Transformation file configuration not found...");
         }
         String tranformationConfString = new Scanner(inputStream, StandardCharsets.UTF_8)
            .useDelimiter("\\A").next();
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.readValue(tranformationConfString, new TypeReference<>() {});
      } catch (Exception e) {
         throw new RuntimeException("Failed to read transformation.json...", e);
      }
   }

}
