package base;

import base.functionalInterfaces.WaitForActionInterface;
import base.functionalInterfaces.WaitForConditionInterface;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class DriverFacade {

    private final WebDriver webDriver;

    private final short SHORT_TIMEOUT = 10;

    public DriverFacade (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openUrl(String url) {
        this.webDriver.get(url);
        this.webDriver.manage().window().maximize();
    }

    public void waitForCondition(WaitForConditionInterface wait) {
        await().atMost(SHORT_TIMEOUT, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until( ()->{
                            try{
                                return wait.waitForCondition();
                            } catch (NoSuchElementException | StaleElementReferenceException ne) {
                                return false;
                            }
                        }
                );
    }

    public void waitForAction(WaitForActionInterface wait) throws RuntimeException {
        await().atMost(SHORT_TIMEOUT, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until( ()->{
                            try{
                                wait.waitForAction();
                                return true;
                            } catch (NoSuchElementException |
                                    StaleElementReferenceException |
                                    ElementNotInteractableException ne) {
                                return false;
                            }
                        }
                );
    }

    public WebElement findElement(By by) throws RuntimeException {
        return this.webDriver.findElement(by);
    }

    public List<WebElement> findElements(By by) throws RuntimeException {
        return this.webDriver.findElements(by);
    }

    public void quit(){
        this.webDriver.quit();
    }
}
