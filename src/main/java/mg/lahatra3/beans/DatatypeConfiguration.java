package mg.lahatra3.beans;

import java.util.Arrays;
import java.util.List;

public enum DatatypeConfiguration {
   BOOLEAN, INTEGER, LONG, DOUBLE, TIMESTAMP, DATE, STRING;

   public static final List<String> DATATYPE_CONFIGURATION = Arrays.stream(
         DatatypeConfiguration.values()
      )
      .map(Enum::name)
      .toList();
}
