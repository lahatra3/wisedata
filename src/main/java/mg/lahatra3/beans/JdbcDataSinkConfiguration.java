package mg.lahatra3.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JdbcDataSinkConfiguration {
    private final String jdbcurl;
    private final String user;
    private final String password;
    private final String dbtable;
    private final String numPartitions;
    private final String batchSize;
}
