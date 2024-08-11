package mg.lahatra3.beans;

import java.util.Optional;

import lombok.Getter;

@Getter
public class WisedataConfiguration {

    private SparkConfiguration sparkConfiguration;
    private JdbcDataSourceConfiguration jdbcDataSourceConfiguration;
    private JdbcDataSinkConfiguration jdbcDataSinkConfiguration;

    public WisedataConfiguration() {
        setSparkConfiguration();
        setDataSourceConfiguration();   
        setDataSinkConfiguration();
    }


    private void setDataSourceConfiguration() {
        String jdbcUrl = getRequiredConfigurationValue("DATA_SOURCE_URL");
        String user = getRequiredConfigurationValue("DATA_SOURCE_USER");
        String passsword = getRequiredConfigurationValue("DATA_SOURCE_PASSWORD");
        String dbtable = getRequiredConfigurationValue("DATA_SOURCE_TABLE");
        String numPartitions = getConfigurationValue("DATA_SOURCE_NUM_PARTITIONS", "3");
        String fetchSize = getConfigurationValue("DATA_SOURCE_FETCH_SIZE", "1000");
        String partitionColumn = getRequiredConfigurationValue("DATA_SOURCE_PARTITION_COLUMN");
        String lowerBound = getConfigurationValue("DATA_SOURCE_LOWER_BOUND", "1");
        String upperBound = getConfigurationValue("DATA_SOURCE_UPPER_BOUND", "1000");

        this.jdbcDataSourceConfiguration = new JdbcDataSourceConfiguration(jdbcUrl, user, passsword, dbtable, numPartitions, fetchSize, partitionColumn, lowerBound, upperBound);
    }

    private void setDataSinkConfiguration() {
       String jdbcurl = getRequiredConfigurationValue("DATA_SINK_URL");
       String user = getRequiredConfigurationValue("DATA_SINK_USER");
       String password = getRequiredConfigurationValue("DATA_SINK_PASSWORD");
       String dbtable = getRequiredConfigurationValue("DATA_SINK_TABLE");
       String numPartitions = getConfigurationValue("DATA_SINK_NUM_PARTITIONS", "3");
       String batchSize = getConfigurationValue("DATA_SINK_BATCH_SIZE", "1000");

        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(jdbcurl, user, password, dbtable, numPartitions, batchSize);
    }

    private void setSparkConfiguration() {
        String appName = getConfigurationValue("SPARK_APP_NAME", "wisedata");
        String masterUrl = getConfigurationValue("SPARK_MASTER_URL", "local[*]");
        String extraJavaOptions = getConfigurationValue("SPARK_EXTRA_JAVA_OPTIONS", null);

        this.sparkConfiguration = new SparkConfiguration(appName, masterUrl, extraJavaOptions);
    }

    private String getRequiredConfigurationValue(String key) {
        return Optional.ofNullable(System.getenv(key))
            .or(() -> Optional.ofNullable(System.getProperty(key)))
            .orElseThrow(() -> new IllegalArgumentException("Missing required configuration: " + key));
    }

    private String getConfigurationValue(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key))
            .or(() -> Optional.ofNullable(System.getProperty(key)))
            .orElse(defaultValue);
    }
}
