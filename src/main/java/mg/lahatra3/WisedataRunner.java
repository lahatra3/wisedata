package mg.lahatra3;

import mg.lahatra3.beans.DatatypeConfiguration;
import mg.lahatra3.functions.DataConversionFunc;
import mg.lahatra3.functions.RenameColumnFunc;
import mg.lahatra3.functions.SelectColumnFunc;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;
import mg.lahatra3.beans.SparkConfiguration;
import mg.lahatra3.readers.JdbcDataReader;
import mg.lahatra3.writers.JdbcDataWriter;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WisedataRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WisedataRunner.class);

    @Override
    public void run() {
       long timeStart = System.currentTimeMillis();

       WisedataConfiguration wisedataConfiguration = new WisedataConfiguration();
       SparkConfiguration sparkConfiguration = wisedataConfiguration.getSparkConfiguration();
       JdbcDataSourceConfiguration dataSourceConfiguration = wisedataConfiguration.getJdbcDataSourceConfiguration();
       JdbcDataSinkConfiguration dataSinkConfiguration = wisedataConfiguration.getJdbcDataSinkConfiguration();
       Map<String, String> columnsMapping = wisedataConfiguration.getColumnsMapping();
       Map<String, DatatypeConfiguration> dataConversion = wisedataConfiguration.getDataConversion();


       SparkSession sparkSession = SparkSession.builder()
          .appName(sparkConfiguration.getAppName())
          .master(sparkConfiguration.getMasterUrl())
          .config("spark.driver.memory", sparkConfiguration.getDriverMemory())
          .config("spark.executor.memory", sparkConfiguration.getExecutorMemory())
          .config("spark.driver.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
          .config("spark.executor.extraJavaOptions", sparkConfiguration.getExtraJavaOptions())
          .getOrCreate();

       logger.info("STARTING TO READ DATA...");
       JdbcDataReader jdbcDataReader = new JdbcDataReader(sparkSession, dataSourceConfiguration);
       Dataset<Row> originDataset = jdbcDataReader.get().persist(StorageLevel.DISK_ONLY_3());

       RenameColumnFunc renameColumnFunc = new RenameColumnFunc(columnsMapping);
       Dataset<Row> transformedDatasetRenameColumn = renameColumnFunc.apply(originDataset);

       SelectColumnFunc selectColumnFunc = new SelectColumnFunc(columnsMapping.values());
       Dataset<Row> transformedDatasetSelectColumn = selectColumnFunc.apply(transformedDatasetRenameColumn);

       DataConversionFunc dataConversionFunc = new DataConversionFunc(dataConversion);
       Dataset<Row> transformedDatasetDataConversion = dataConversionFunc.apply(transformedDatasetSelectColumn);

       logger.info("STARTING TO WRITE DATA...");
       JdbcDataWriter jdbcDataWriter = new JdbcDataWriter(dataSinkConfiguration);
       jdbcDataWriter.accept(transformedDatasetDataConversion);

       originDataset.unpersist(true);

       sparkSession.stop();

       long timeEnd = System.currentTimeMillis();
       long duration = timeEnd - timeStart;

       logger.info("SUCCESSFULLY COMPLETED...");
       logger.info("DURATION: {} ms...", duration);
    }
}
