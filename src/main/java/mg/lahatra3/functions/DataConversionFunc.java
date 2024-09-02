package mg.lahatra3.functions;

import lombok.RequiredArgsConstructor;
import mg.lahatra3.beans.DatatypeConfiguration;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DataConversionFunc implements UnaryOperator<Dataset<Row>> {
   private final Map<String, DatatypeConfiguration> dataConversion;

   @Override
   public Dataset<Row> apply(Dataset<Row> dataset) {
      if (dataConversion.isEmpty()) {
         return dataset;
      }
      Map<String, Column> columnsConversionMapping = dataConversion
         .entrySet()
         .parallelStream()
         .collect(
            Collectors.toMap(
               Map.Entry::getKey,
               entry -> getColumn(
                  entry.getKey(),
                  getDatatype(entry.getValue())
               )
            )
         );
      return dataset.withColumns(columnsConversionMapping);
   }

   private Column getColumn(String columnName, DataType dataType) {
      return functions.col(columnName).cast(dataType);
   }

   private DataType getDatatype(DatatypeConfiguration datatypeConfiguration) {
      return switch (datatypeConfiguration) {
         case BOOLEAN -> DataTypes.BooleanType;
         case INTEGER -> DataTypes.IntegerType;
         case LONG -> DataTypes.LongType;
         case DOUBLE -> DataTypes.DoubleType;
         case TIMESTAMP -> DataTypes.TimestampType;
         case DATE -> DataTypes.DateType;
         case STRING -> DataTypes.StringType;
      };
   }
}
