import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Target_5 {
    private static long timeWaitingInSec1 = 60;
    private static long timeWaitingInSec2 = 2000;

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
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

        SearchQueryPage searchPage = new SearchQueryPage(driver);
        if (!searchPage.setImplicitlyWait(timeWaitingInSec1).equals("OK"))
            System.out.println("Проверьте на корректность поле 'timeWaitingInSec1'!");

        ResponsePage responsePage = searchPage.searchQuery("Кемерово", "Москва", "7 июля");

        List<WebElement> articles = responsePage.getArticles();
        int line = 0;
        int countException = 0;
        for (WebElement art: articles)
        {
            line ++;
            String checkRouteForArt = responsePage.checkRouteForArticle(art);
            if (!checkRouteForArt.equals(responsePage.OK)) {
                if (checkRouteForArt.contains(responsePage.exc_EMPTY)){
                    System.err.println("    Ошибка: Для " + line + " строки расписания задан пустой рейс.");
                    countException++;
                }
                if (checkRouteForArt.contains(responsePage.exc_FIELD_NOT_EXIST)) {
                    System.err.println("    Ошибка: Для " + line + " строки расписания не задано поле для названия рейса." );
                    countException++;
                }
            }

            String checkTravelTimeForArt = responsePage.checkTravelTimeForArticle(art);
            if (!checkTravelTimeForArt.equals(responsePage.OK)) {
                if (checkTravelTimeForArt.contains(responsePage.exc_EMPTY)){
                    System.err.println("    Ошибка: Для " + line + " строки расписания не задано время в пути.");
                    countException++;
                }
                if (checkTravelTimeForArt.contains(responsePage.exc_FIELD_NOT_EXIST)) {
                    System.err.println("    Ошибка: Для " + line + " строки расписания не задано поле для указания " +
                            "времени в пути." );
                    countException++;
                }
            }

            String checkIconForArt = responsePage.checkIconForArticle(art);
            if (checkIconForArt.contains(responsePage.exc_FIELD_NOT_EXIST)){
                System.err.println("    Ошибка: Для " + line + " строки расписания не задан '<svg>' для иконки транспорта.");
                countException++;
            }
        }

        if (articles.size() != 5)
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

        SearchQueryPage searchPage = new SearchQueryPage(driver);
        if (!searchPage.setImplicitlyWait(timeWaitingInSec2).equals(searchPage.OK))
            System.out.println("Проверьте на корректность поле 'timeWaitingInSec1'!");

        Calendar calendar = new GregorianCalendar();
        // Если бы этот сервис понимал запись '7 июнь', то можно было бы воспользоваться классом SimpleDateFormat, но он
        // такое не принимает и просит меня выбрать дату в календаре, а по заданию дату нужно вписать.
        // Поэтому здесь появилось это:
        toNearWednesday(calendar);
        String data = getDayAndMonth(calendar);

        searchPage.fullAllFields("Кемерово проспект Ленина", "Кемерово Бакинский переулок", data);
        searchPage.clickOnButBus();
        ResponsePage responsePage = searchPage.clickButFind();

        String checkErrorPage = responsePage.checkErrorPage();
        if (checkErrorPage.equals(responsePage.OK)) {
            WebElement errorpage = responsePage.getErrorPage();
            String erPointOfDest = "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.";
            String checkErrorMessage = responsePage.checkErrorMessage(errorpage, erPointOfDest);
            if (checkErrorMessage.equals(responsePage.exc_FIELD_NOT_EXIST)) {
                System.err.println("Ошибка: Ошибка с текстом '" + erPointOfDest + "' не найдена.");
                return false;
            }
            else
                return true;
        } else {
            System.err.println("Ух ты! Похоже, по Вашему запросу что-то нашлось, и это не страница с ошибкой.");
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
