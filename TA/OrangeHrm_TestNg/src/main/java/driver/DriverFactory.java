package driver;

import enums.Modes;
import org.openqa.selenium.WebDriver;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {
    private DriverFactory(){}

    private static final Supplier<WebDriver> LOCAL = ()-> new LocalDriverImpl().getDriver();

    private static final Map<Modes, Supplier<WebDriver>> MAP = new EnumMap<>(Modes.class);

    static {
        MAP.put(Modes.LOCAL,LOCAL);
    }

    public static WebDriver getDriver(Modes modes){
        return MAP.get(modes).get();
    }
}
