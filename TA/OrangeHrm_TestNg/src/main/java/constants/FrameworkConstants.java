package constants;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
public final class FrameworkConstants {
    private FrameworkConstants() {
    }

    private static FrameworkConstants frameworkConstants;

    public static synchronized FrameworkConstants getInstance(){
        if (Objects.isNull(frameworkConstants)){
            frameworkConstants = new FrameworkConstants();
        }
        return frameworkConstants;
    }
    private final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";

    @Getter
    private static final int EXPLICITLYWAIT = 15;

    @Getter
    private  final String testExcelPath = RESOURCESPATH + "/Excel/TestData.xlsx";;
    @Getter
    private  final String password ="";
}
