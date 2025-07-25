package reports;



import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import enums.LogType;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import static reports.ExtentManager.*;
import static utils.ScreenShotUtils.getBase64Images;


public class FrameWorkLogger {
    private static final Consumer<String> PASS = (message) -> getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    private static final Consumer<String> FAIL = (message) -> getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    private static final Consumer<String> INFO = (message) -> getExtentTest().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
    private static final Consumer<String> SKIP = (message) -> getExtentTest().skip(MarkupHelper.createLabel(message, ExtentColor.PINK));
    private static final Consumer<String> SCREEN_CAPTURE = (message) -> getExtentTest().fail(createScreenCaptureFromBase64String(getBase64Images()).build());
    private static final Consumer<String> CONSOLE = System.out::println;
    private static final Consumer<String> EXTENT_AND_CONSOLE = PASS.andThen(CONSOLE);
    private static final Consumer<String> PASS_AND_SCREEN_CAPTURE = (message) -> getExtentTest().pass(createScreenCaptureFromBase64String(getBase64Images()).build());
    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);


    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENT_AND_CONSOLE, EXTENT_AND_CONSOLE);
        MAP.put(LogType.SCREEN_CAPTURE, SCREEN_CAPTURE);
        MAP.put(LogType.PASS_AND_SCREEN_CAPTURE, PASS_AND_SCREEN_CAPTURE);

    }

    private FrameWorkLogger() {
    }

    public static String log(LogType logType, String message) {
        MAP.get(logType).accept(message);
        return message;
    }


}
