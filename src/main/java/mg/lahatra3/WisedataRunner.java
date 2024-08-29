package mg.lahatra3;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;
import mg.lahatra3.beans.SparkConfiguration;
import mg.lahatra3.readers.JdbcDataReader;
import mg.lahatra3.writers.JdbcDataWriter;

public class WisedataRunner implements Runnable {

    @Override
    public void run() {
        WisedataConfiguration wisedataConfiguration = new WisedataConfiguration();
        SparkConfiguration sparkConfiguration = wisedataConfiguration.getSparkConfiguration();
        JdbcDataSourceConfiguration dataSourceConfiguration = wisedataConfiguration.getJdbcDataSourceConfiguration();
        JdbcDataSinkConfiguration dataSinkConfiguration = wisedataConfiguration.getJdbcDataSinkConfiguration();

        SparkSession sparkSession = SparkSession.builder()
           .appName(sparkConfiguration.getAppName())
           .master(sparkConfiguration.getMasterUrl())
           .config("spark.driver.memory", sparkConfiguration.getDriverMemory())
           .config("spark.executor.memory", sparkConfiguration.getExecutorMemory())
           .config("spark.driver.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
           .config("spark.executor.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
           .getOrCreate();

        long timeStart = System.currentTimeMillis();

        System.out.println("Starting to read data...");
        JdbcDataReader jdbcDataReader = new JdbcDataReader(sparkSession, dataSourceConfiguration);
        Dataset<Row> dataset = jdbcDataReader.get();

        System.out.println("Starting to write data...");
        JdbcDataWriter jdbcDataWriter = new JdbcDataWriter(dataSinkConfiguration);
        jdbcDataWriter.accept(dataset);

        long timeEnd = System.currentTimeMillis();
        long duration = timeEnd - timeStart;

        sparkSession.stop();

        System.out.println("Successfully completed...");
        System.out.println("Duration: " + duration + "ms...");
    }
}
