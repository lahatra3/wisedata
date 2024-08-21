package mg.lahatra3.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SparkConfiguration {
    private final String appName;
    private final String masterUrl;
    private final String driverMemory;
    private final String extraJavaOptions;
}
