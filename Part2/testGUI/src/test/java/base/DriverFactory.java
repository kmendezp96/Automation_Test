package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private DriverFacade webDriver;

    public DriverFacade getDriver(String browser) {
        switch (browser) {
            case "Firefox":
                this.webDriver = new DriverFacade(new FirefoxDriver());
                break;
            default:
                this.webDriver = new DriverFacade(new ChromeDriver());
                break;
        }
        return this.webDriver;
    }


}
