package WebServices.utils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.UUID;

public class Header {

    private static String correlationID = UUID.randomUUID().toString();
    private static String causationID = "";

    @Before("@API")
    public void getScenarioName(Scenario scenario) {
        causationID =  scenario.getName();
    }

    public static String getCorrelationId() {
        return correlationID;
    }

    public static void setCausationID(String causationID) {
        Header.causationID = causationID;
    }

    public static String getCausationID() {
        return causationID;
    }
}