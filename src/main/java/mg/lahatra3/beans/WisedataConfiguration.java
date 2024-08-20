package mg.lahatra3.beans;

import lombok.Getter;
import mg.lahatra3.utils.Env4j;

import static mg.lahatra3.utils.ConstantUtils.*;

@Getter
public class WisedataConfiguration {

    private SparkConfiguration sparkConfiguration;
    private JdbcDataSourceConfiguration jdbcDataSourceConfiguration;
    private JdbcDataSinkConfiguration jdbcDataSinkConfiguration;
    private final Env4j env4j = new Env4j();

    public WisedataConfiguration() {
        setSparkConfiguration();
        setDataSourceConfiguration();
        setDataSinkConfiguration();
    }

    private void setDataSourceConfiguration() {
        String jdbcUrl = env4j.get("DATA_SOURCE_URL");
        String user = env4j.get("DATA_SOURCE_USER");
        String password = env4j.get("DATA_SOURCE_PASSWORD");
        String dbtable = env4j.get("DATA_SOURCE_TABLE");
        String numPartitions = env4j.get("DATA_SOURCE_NUM_PARTITIONS", DEFAULT_NUM_PARTITIONS);
        String fetchSize = env4j.get("DATA_SOURCE_FETCH_SIZE", DEFAULT_BATCH_SIZE);
        String partitionColumn = env4j.get("DATA_SOURCE_PARTITION_COLUMN");
        String lowerBound = env4j.get("DATA_SOURCE_LOWER_BOUND");
        String upperBound = env4j.get("DATA_SOURCE_UPPER_BOUND");

        this.jdbcDataSourceConfiguration = new JdbcDataSourceConfiguration(jdbcUrl, user, password, dbtable,
            numPartitions, fetchSize, partitionColumn, lowerBound, upperBound);
    }

    private void setDataSinkConfiguration() {
        String jdbcUrl = env4j.get("DATA_SINK_URL");
        String user = env4j.get("DATA_SINK_USER");
        String password = env4j.get("DATA_SINK_PASSWORD");
        String dbtable = env4j.get("DATA_SINK_TABLE");
        String numPartitions = env4j.get("DATA_SINK_NUM_PARTITIONS", DEFAULT_NUM_PARTITIONS);
        String batchSize = env4j.get("DATA_SINK_BATCH_SIZE", DEFAULT_BATCH_SIZE);

        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(jdbcUrl, user, password, dbtable, numPartitions,
            batchSize);
    }

    private void setSparkConfiguration() {
        String appName = env4j.get("SPARK_APP_NAME", DEFAULT_SPARK_APP_NAME);
        String masterUrl = env4j.get("SPARK_MASTER_URL", DEFAULT_SPARK_MASTER_URL);
        String driverMemory = env4j.get("SPARK_DRIVER_MEMORY", DEFAULT_SPARK_DRIVER_MEMORY);
        String executorMemory = env4j.get("SPARK_EXECUTOR_MEMORY", DEFAULT_SPARK_EXECUTOR_MEMORY);
        String extraJavaOptions = env4j.get("SPARK_EXTRA_JAVA_OPTIONS", DEFAULT_SPARK_EXTRA_JAVA_OPTIONS);

        this.sparkConfiguration = new SparkConfiguration(appName, masterUrl, driverMemory, executorMemory, extraJavaOptions);
    }
}
