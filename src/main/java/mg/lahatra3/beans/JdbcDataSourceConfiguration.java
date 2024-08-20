package mg.lahatra3.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JdbcDataSourceConfiguration {
    private String jdbcUrl;
    private String user;
    private String password;
    private String dbtable;
    private String numPartitions;
    private String fetchSize;
    private String partitionColumn;
    private String lowerBound;
    private String upperBound;
}
