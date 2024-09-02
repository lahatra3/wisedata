package mg.lahatra3.functions;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.Collection;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
public class SelectColumnFunc implements UnaryOperator<Dataset<Row>> {
   private final Collection<String> columns;

   @Override
   public Dataset<Row> apply(Dataset<Row> dataset) {
      if (columns.isEmpty()) {
         return dataset;
      }
      return dataset.selectExpr(columns.toArray(String[]::new));
   }

}
