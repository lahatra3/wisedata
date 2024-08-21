package mg.lahatra3.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantUtils {
    public static final String DEFAULT_SPARK_APP_NAME = "wisedata";
    public static final String DEFAULT_SPARK_MASTER_URL = "local[*]";
    public static final String DEFAULT_SPARK_DRIVER_MEMORY = "1g";
    public static final String DEFAULT_SPARK_EXTRA_JAVA_OPTIONS = "-XX:+IgnoreUnrecognizedVMOptions --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens=java.base/sun.nio.cs=ALL-UNNAMED --add-opens=java.base/sun.security.action=ALL-UNNAMED --add-opens=java.base/sun.util.calendar=ALL-UNNAMED --add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED";
    public static final String DEFAULT_NUM_PARTITIONS = "7";
    public static final String DEFAULT_BATCH_SIZE = "1331";
}
