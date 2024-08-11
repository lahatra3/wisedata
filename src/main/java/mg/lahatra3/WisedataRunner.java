package mg.lahatra3;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;
import mg.lahatra3.beans.SparkConfiguration;
import mg.lahatra3.beans.WisedataConfiguration;
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
            .getOrCreate();

        JdbcDataReader jdbcDataReader = new JdbcDataReader(sparkSession, dataSourceConfiguration);
        Dataset<Row> dataset = jdbcDataReader.get();

        JdbcDataWriter jdbcDataWriter = new JdbcDataWriter(dataSinkConfiguration);
        jdbcDataWriter.accept(dataset);

        sparkSession.stop();
    }
}
