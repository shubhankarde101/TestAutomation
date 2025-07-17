package WebServices.utils;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionEvaluationLogger;
import org.awaitility.core.ConditionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Await {

    private static final String PROPERTIES_FILE = "serenity.properties";
    private static final int waitingTime;
    private static final int pollingInterval;

    public static ConditionFactory waitForWithParameters(int timeout, int pollingInterval, int delay) {
        Awaitility.await().atMost(timeout, TimeUnit.SECONDS)
                .with().pollInterval(pollingInterval, TimeUnit.MILLISECONDS);
        if (delay > 0) {
            Awaitility.await().pollDelay(delay, TimeUnit.MILLISECONDS);
        }
        return Awaitility.await();
    }

    public static ConditionFactory waitFor() {
        return Awaitility.await().atMost(waitingTime,  TimeUnit.SECONDS)
                .with().conditionEvaluationListener(new ConditionEvaluationLogger()).pollInterval(pollingInterval, TimeUnit.MILLISECONDS);
    }

    static {
        try {
            Properties credentialsProperties = new Properties();
            credentialsProperties.load(new FileInputStream(PROPERTIES_FILE));
            waitingTime = Integer.parseInt(credentialsProperties.getProperty("awaitility.wait.time"));
            pollingInterval = Integer.parseInt(credentialsProperties.getProperty("awaitility.polling.interval"));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
