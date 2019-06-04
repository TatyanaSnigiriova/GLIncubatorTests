import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResponsePage {

    private WebDriver driver;

    public ResponsePage(WebDriver driver) {
        this.driver = driver;
    }

    private String xPathAllArticles = "//article";

    public List<WebElement> getArticles() {
        return this.driver.findElements(By.xpath(xPathAllArticles));
    }

    public String OK = "OK";
    public String exc_EMPTY = "Empty";
    public String exc_FIELD_NOT_EXIST = "No field";


    private String xPathRoute = "//span[@class='SegmentTitle__number']";
    private String xPathTravelTime = "//div[contains(@class, 'duration')]";
    private String xPathIcon = "//*[@class = 'TransportIcon__icon']";

    private String xPathErrorPage = "//div[@class='ErrorPage']";
    private String xPathPattern = "//div[text()='%s']";


    private boolean IsElementFound(String xPath) {
        try {
            this.driver.findElement(By.xpath(xPath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String checkRouteForArticle(WebElement art) {
        if (IsElementFound(xPathAllArticles.concat(xPathRoute))) {
            WebElement el = art.findElement(By.xpath(xPathRoute));
            if (el.getText().equals(""))
                return exc_EMPTY.concat(" route");
            else
                return OK;
        } else
            return exc_FIELD_NOT_EXIST.concat(" for route");
    }

    public String checkTravelTimeForArticle(WebElement art) {
        if (IsElementFound(xPathAllArticles.concat(xPathTravelTime))) {
            WebElement el = art.findElement(By.xpath(xPathTravelTime));
            if (el.getText().equals(""))
                return exc_EMPTY.concat(" travel time");
            else
                return OK;
        } else
            return exc_FIELD_NOT_EXIST.concat(" for travel time");
    }

    public String checkIconForArticle(WebElement art) {
        if (!IsElementFound(xPathAllArticles.concat(xPathIcon)))
            return exc_FIELD_NOT_EXIST.concat(" for icon");
        else
            return OK;
    }

    public String checkErrorPage() {
        if (IsElementFound(xPathErrorPage))
            return OK;
        else
            return exc_FIELD_NOT_EXIST;

    }

    public WebElement getErrorPage() {
        return driver.findElement(By.xpath(xPathErrorPage));
    }

    public String checkErrorMessage(WebElement errorPage, String message) {
        String xPath = String.format(xPathPattern, message);
        if (IsElementFound(xPathErrorPage.concat(xPath)))
            return OK;
        else
            return exc_FIELD_NOT_EXIST;
    }
}
