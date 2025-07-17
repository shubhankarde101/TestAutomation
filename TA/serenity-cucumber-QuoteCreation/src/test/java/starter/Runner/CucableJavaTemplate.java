package starter.Runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"}

)
public class CucableJavaTemplate {
    // [CUCABLE:CUSTOM:comment]
}
