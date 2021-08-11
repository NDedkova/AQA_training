package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeTest(Scenario scenario) {
        log.info("--- Старт сценария '" + scenario.getName() + "' ---");

    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            log.warn("--- Сценарий '" + scenario.getName() + "' провалился ---");
        } else {
            log.debug("--- Сценарий '" + scenario.getName() + "' выполнен успешно ---");
        }
    }
}
