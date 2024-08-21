package mg.lahatra3.beans;

import lombok.Getter;
import mg.lahatra3.utils.Env4j;
import mg.lahatra3.utils.ConstantUtils;

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

    private void setSparkConfiguration() {
        String appName = env4j.get("SPARK_APP_NAME", ConstantUtils.DEFAULT_SPARK_APP_NAME);
        String masterUrl = env4j.get("SPARK_MASTER_URL", ConstantUtils.DEFAULT_SPARK_MASTER_URL);
        String driverMemory = env4j.get("SPARK_DRIVER_MEMORY", ConstantUtils.DEFAULT_SPARK_DRIVER_MEMORY);
        String extraJavaOptions = env4j.get("SPARK_EXTRA_JAVA_OPTIONS", ConstantUtils.DEFAULT_SPARK_EXTRA_JAVA_OPTIONS);

        this.sparkConfiguration = new SparkConfiguration(appName, masterUrl, driverMemory, extraJavaOptions);
    }

    private void setDataSourceConfiguration() {
        String jdbcUrl = env4j.get("DATA_SOURCE_URL");
        String user = env4j.get("DATA_SOURCE_USER");
        String passsword = env4j.get("DATA_SOURCE_PASSWORD");
        String dbtable = env4j.get("DATA_SOURCE_TABLE");
        String numPartitions = env4j.get("DATA_SOURCE_NUM_PARTITIONS", ConstantUtils.DEFAULT_NUM_PARTITIONS);
        String fetchSize = env4j.get("DATA_SOURCE_FETCH_SIZE", ConstantUtils.DEFAULT_BATCH_SIZE);
        String partitionColumn = env4j.get("DATA_SOURCE_PARTITION_COLUMN");
        String lowerBound = env4j.get("DATA_SOURCE_LOWER_BOUND");
        String upperBound = env4j.get("DATA_SOURCE_UPPER_BOUND");

        this.jdbcDataSourceConfiguration = new JdbcDataSourceConfiguration(jdbcUrl, user, passsword, dbtable,
            numPartitions, fetchSize, partitionColumn, lowerBound, upperBound);
    }

    private void setDataSinkConfiguration() {
        String jdbcurl = env4j.get("DATA_SINK_URL");
        String user = env4j.get("DATA_SINK_USER");
        String password = env4j.get("DATA_SINK_PASSWORD");
        String dbtable = env4j.get("DATA_SINK_TABLE");
        String numPartitions = env4j.get("DATA_SINK_NUM_PARTITIONS", ConstantUtils.DEFAULT_NUM_PARTITIONS);
        String batchSize = env4j.get("DATA_SINK_BATCH_SIZE", ConstantUtils.DEFAULT_BATCH_SIZE);

        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(jdbcurl, user, password, dbtable, numPartitions,
            batchSize);
    }
}
