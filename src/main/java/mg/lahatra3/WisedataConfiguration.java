package mg.lahatra3;

import lombok.Getter;
import mg.lahatra3.beans.JdbcDataSinkConfiguration;
import mg.lahatra3.beans.JdbcDataSourceConfiguration;
import mg.lahatra3.beans.SparkConfiguration;

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

        this.jdbcDataSourceConfiguration = new JdbcDataSourceConfiguration(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        );
    }

    private void setDataSinkConfiguration() {

        this.jdbcDataSinkConfiguration = new JdbcDataSinkConfiguration(
            "",
            "",
            "",
            "",
            "",
            ""
        );
    }

    private void setSparkConfiguration() {

        this.sparkConfiguration = new SparkConfiguration(
            "Wisedata",
            "local-cluster[2, 2, 1024]",
            "-XX:+IgnoreUnrecognizedVMOptions --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens=java.base/sun.nio.cs=ALL-UNNAMED --add-opens=java.base/sun.security.action=ALL-UNNAMED --add-opens=java.base/sun.util.calendar=ALL-UNNAMED --add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED"
        );
    }
}
