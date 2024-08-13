package mg.lahatra3.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JdbcDataSourceConfiguration {
    private final String jdbcUrl;
    private final String user;
    private final String password;
    private final String dbtable;
    private final String numPartitions;
    private final String fetchSize;
    private final String partitionColumn;
    private final String lowerBound;
    private final String upperBound;
}
