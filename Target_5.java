import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Target_5
{
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Projects\\GoodLineСases\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        if (case_1(driver))
            System.out.println("    OK!");
        if (case_2(driver))
            System.out.println("    OK!");
//        driver.quit();
    }

    public static boolean case_1(WebDriver driver)
    {
        System.out.println("Case 1:");

        driver.get("https://rasp.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        putInField(driver, "from", "Кемерово");
        putInField(driver, "to", "Москва");
        putInField(driver, "when", "7 июля");

        driver.findElement(By.xpath("//span[text()='Найти']/ancestor::button")).click();


        String xPathBegin = "//article";
        List<WebElement> article = driver.findElements(By.xpath(xPathBegin));
        int line = 0;
        int countException = 0;
        for (WebElement art: article)
        {
            line ++;
            String xPathEnd = "//span[@class='SegmentTitle__number']";
            if (IsElementFound(driver, xPathBegin.concat(xPathEnd))) {
                WebElement el = art.findElement(By.xpath(xPathEnd));
                if (el.getText().equals("")) {
                    System.err.println("    Ошибка: Для " + line + " строки расписания задан пустой рейс.");
                    countException++;
                }
            }
            else {
                System.err.println("    Ошибка: Для " + line + " строки расписания не задано поле для названия рейса." );
                countException++;
            }

            xPathEnd = "//div[contains(@class, 'duration')]";
            if (IsElementFound(driver, xPathBegin.concat(xPathEnd))) {
                WebElement el = art.findElement(By.xpath(xPathEnd));
                if (el.getText().equals("")) {
                    System.err.println("    Ошибка: Для " + line + " строки расписания не задано время в пути.");
                    countException++;
                }
            }
            else {
                System.err.println("    Ошибка: Для " + line + " строки расписания не задано поле для указания " +
                        "времени в пути.");
                countException++;
            }

            xPathEnd = "//*[@class = 'TransportIcon__icon']";
            if (!IsElementFound(driver, xPathBegin.concat(xPathEnd))) {
                System.out.println("    Ошибка: Для " + Integer.toString(line) + " строки расписания не задан '<svg>' для иконки транспорта.");
                countException++;
            }
        }
        if (article.size() != 5)
        {
            System.err.println("    Ошибка: По запросу выдано не 5 рейсов.");
            countException++;
        }
        if (countException == 0)
            return true;
        else
            return false;
    }

    public static boolean case_2(WebDriver driver)
    {
        System.out.println("Case 2:");

        driver.get("https://rasp.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        putInField(driver, "from", "Кемерово проспект Ленина");
        putInField(driver, "to", "Кемерово Бакинский переулок");
        Calendar calendar = new GregorianCalendar();
        // Если бы этот сервис понимал запись '7 июнь', то можно было бы воспользоваться классом SimpleDateFormat, но он
        // такое не принимает и просит меня выбрать дату в календаре, а по заданию дату нужно вписать.
        // Поэтому здесь появилось это:
        toNearWednesday(calendar);
        String data = getDayAndMonth(calendar);

        putInField(driver, "when", data );

        driver.findElement(By.xpath("//div[@class='TransportSelector']//span[text()='Автобус']")).click();
        driver.findElement(By.xpath("//span[text()='Найти']/ancestor::button")).click();

        String xPathBegin = "//div[@class='ErrorPage']";
        if (IsElementFound(driver, xPathBegin))
        {
            WebElement errorpage = driver.findElement(By.xpath(xPathBegin));
            String erPointOfDest = "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.";
            String xPathPattern = "//div[text()='%s']";
            String xPathEnd = String.format(xPathPattern, erPointOfDest);
            if (IsElementFound (driver, xPathBegin.concat(xPathEnd)))
                return  true;
            else{
                System.err.println("Ошибка: Ошибка с текстом '" + erPointOfDest + "' не найдена.");
                return false;
            }
        }
        else{
            System.err.println("Ух ты! Похоже, по Вашему запросу что-то нашлось, и это не страница с ошибкой.");
            return false;
        }
    }


    private static void putInField(WebDriver driver, String id, String value)
    {
        WebElement from = driver.findElement(By.id(id));
        from.clear();
        from.sendKeys(value);
    }


    private static boolean IsElementFound (WebDriver driver, String xPath)
    {
        try {
            driver.findElement(By.xpath(xPath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    private static void toNearWednesday(Calendar calendar) {

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 3);
                break;
            case Calendar.MONDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 2);
                break;
            case Calendar.TUESDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case Calendar.WEDNESDAY:
                break;
            case Calendar.THURSDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 6);
                break;
            case Calendar.FRIDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 5);
                break;
            case Calendar.SATURDAY:
                calendar.add(Calendar.DAY_OF_MONTH, 4);
                break;
        }
    }

    private static String getDayAndMonth (Calendar calendar){
        String data = calendar.get(Calendar.DAY_OF_MONTH) + " ";

        switch (calendar.get(Calendar.MONTH))
        {
            case Calendar.JANUARY:
                data.concat("января");
                break;
            case Calendar.FEBRUARY:
                data.concat("февраля");
                break;
            case Calendar.MARCH:
                data.concat("марта");
                break;
            case Calendar.APRIL:
                data.concat("апреля");
                break;
            case Calendar.MAY:
                data.concat("мая");
                break;
            case Calendar.JUNE:
                data.concat("июня");
                break;
            case Calendar.JULY:
                data.concat("июля");
                break;
            case Calendar.AUGUST:
                data.concat("августа");
                break;
            case Calendar.SEPTEMBER:
                data.concat("сентебря");
                break;
            case Calendar.OCTOBER:
                data.concat("октября");
                break;
            case Calendar.NOVEMBER:
                data.concat("ноября");
                break;
            case Calendar.DECEMBER:
                data.concat("декабря");
                break;
        }
        return data;
    }
}
