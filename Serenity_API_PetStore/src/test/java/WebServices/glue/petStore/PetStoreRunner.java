package WebServices.glue.petStore;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/petStore/PetStoreUser.feature",
        glue = "WebServices",
        plugin = {"json:target/cucumber-report.json", "WebServices.utils.Listener"}
)

public class PetStoreRunner {}