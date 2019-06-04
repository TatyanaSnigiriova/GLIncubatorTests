import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class SearchQueryPage {
    private WebDriver driver;
    public String OK = "OK";

    public SearchQueryPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://rasp.yandex.ru/");
    }

    public String setImplicitlyWait(long seconds) {
        if (seconds >= 0) {
            this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
            return OK;
        } else
            return "Wrong time";
    }

    private String idInFrom = "from";
    private String idInTo = "to";
    private String idInWhen = "when";

    public String getIdInFrom() {
        return idInFrom;
    }

    public String getIdInTo() {
        return idInTo;
    }

    public String getIdInWhen() {
        return idInWhen;
    }

    private By xPathButFind = By.xpath("//span[text()='Найти']/ancestor::button");
    private By xPathButBus = By.xpath("//div[@class='TransportSelector']//span[text()='Автобус']");


    public SearchQueryPage putInField(String id, String value) {
        WebElement from = driver.findElement(By.id(id));
        from.clear();
        from.sendKeys(value);
        return this;
    }

    public SearchQueryPage fullAllFields(String from, String to, String when) {
        putInField(idInFrom, from);
        putInField(idInTo, to);
        putInField(idInWhen, when);
        return this;
    }

    public ResponsePage clickButFind() {
        driver.findElement(xPathButFind).click();
        return new ResponsePage(driver);
    }

    public ResponsePage searchQuery(String from, String to, String when) {
        fullAllFields(from, to, when);
        return clickButFind();
    }

    public SearchQueryPage clickOnButBus() {
        driver.findElement(xPathButBus).click();
        return this;
    }
}
