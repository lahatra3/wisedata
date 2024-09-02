package mg.lahatra3;

import lombok.Getter;
import mg.lahatra3.beans.DatatypeConfiguration;
import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;
import mg.lahatra3.beans.SparkConfiguration;

import java.util.Map;
import java.util.stream.Collectors;

import static mg.lahatra3.utils.ConfigurationUtils.*;

@Getter
public class WisedataConfiguration {
    private SparkConfiguration sparkConfiguration;
    private JdbcDataSourceConfiguration jdbcDataSourceConfiguration;
    private JdbcDataSinkConfiguration jdbcDataSinkConfiguration;
    private Map<String, String> columnsMapping;
    private Map<String, DatatypeConfiguration> dataConversion;

    public WisedataConfiguration() {
        setSparkConfiguration();
        setDataSourceConfiguration();
        setDataSinkConfiguration();
        setColumnsMapping();
        setDataConversion();
    }

    private void setSparkConfiguration() {
        this.sparkConfiguration = new SparkConfiguration(SPARK_APP_NAME, SPARK_MASTER_URL,
           SPARK_DRIVER_MEMORY, SPARK_EXECUTOR_MEMORY, SPARK_EXTRA_JAVA_OPTIONS);
    }

    private void setDataSourceConfiguration() {
        this.jdbcDataSourceConfiguration = new JdbcDataSourceConfiguration(DATA_SOURCE_URL,
           DATA_SOURCE_USER, DATA_SOURCE_PASSWORD, DATA_SOURCE_TABLE, DATA_SOURCE_NUM_PARTITIONS,
           DATA_SOURCE_FETCH_SIZE, DATA_SOURCE_PARTITION_COLUMN, DATA_SOURCE_LOWER_BOUND,
           DATA_SOURCE_UPPER_BOUND);
    }

    private void setDataSinkConfiguration() {
        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(DATA_SINK_URL,
           DATA_SINK_USER, DATA_SINK_PASSWORD, DATA_SINK_TABLE, DATA_SINK_NUM_PARTITIONS,
           DATA_SINK_BATCH_SIZE);
    }

    private void setColumnsMapping() {
        this.columnsMapping = DATA_TRANSFORMATION.get("columns_mapping");
    }

    private void setDataConversion() {
        this.dataConversion = DATA_TRANSFORMATION.get("data_conversion")
           .entrySet()
           .parallelStream()
           .collect(
              Collectors.toMap(
                  Map.Entry::getKey,
                  entry -> DatatypeConfiguration.valueOf(entry.getValue())
              )
           );
    }

}
