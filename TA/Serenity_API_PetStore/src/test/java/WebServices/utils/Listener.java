package WebServices.utils;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.util.List;

public class Listener implements ConcurrentEventListener {

    private static List<String> TAG_NAMES = null;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, testCaseStartedEventHandler);
    }

    private EventHandler<TestCaseStarted> testCaseStartedEventHandler = new EventHandler<TestCaseStarted>() {
        @Override
        public void receive(TestCaseStarted event) {
            handleTestCaseStarted(event);
        }
    };

    private void handleTestCaseStarted(TestCaseStarted event) {
        TAG_NAMES = event.getTestCase().getTags();
    }

    public static List<String> getTagName() {
        return TAG_NAMES;
    }

}
