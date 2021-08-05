package cucumber.hooks;

import driver.DriverSingleton;
import io.cucumber.java.After;

public class DriverHooks {

    @After
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}
