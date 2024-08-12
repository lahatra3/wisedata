package mg.lahatra3.readers;

import java.util.function.Supplier;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import lombok.RequiredArgsConstructor;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;

@RequiredArgsConstructor
public class JdbcDataReader implements Supplier<Dataset<Row>> {

    private final SparkSession sparkSession;
    private final JdbcDataSourceConfiguration jdbcDataSourceConfiguration;

    @Override
    public Dataset<Row> get() {
        String dbtableQueryStr = "(SELECT * FROM " + jdbcDataSourceConfiguration.getDbtable() + ") wisedata_tmp";

        return sparkSession.read()
            .format("jdbc")
            .option("url", jdbcDataSourceConfiguration.getJdbcUrl())
            .option("user", jdbcDataSourceConfiguration.getUser())
            .option("password", jdbcDataSourceConfiguration.getPassword())
            .option("dbtable", dbtableQueryStr)
            .option("partitionColumn", jdbcDataSourceConfiguration.getPartitionColumn())
            .option("lowerBound", jdbcDataSourceConfiguration.getLowerBound())
            .option("upperBound", jdbcDataSourceConfiguration.getUpperBound())
            .option("numPartitions", jdbcDataSourceConfiguration.getNumPartitions())
            .option("fetchsize", jdbcDataSourceConfiguration.getFetchSize())
            .load();
    }
}
