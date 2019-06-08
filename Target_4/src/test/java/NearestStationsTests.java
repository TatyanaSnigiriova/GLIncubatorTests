import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import io.restassured.response.Response;
import org.json.JSONException;

public class NearestStationsTests extends NearestStations {
    // Обычные тестовые сдучаи:
    //     1) Координаты офиса GoodLine

    @Test
    @Ignore
    public void getRequestExampleTest() throws JSONException {
        Response response = get(
                Config.getHTTPS() + localHTTPS + "/?"+
                "apikey="+Config.getKey()+
                "&"+ r_format +"=json"+
                "&"+ r_lat +"=" + 55.388645 +
                "&"+ r_lng +"="+86.098903 +
                "&"+r_dist +"="+15+
                "&"+r_stat_types +"="+r_airport );
    }

}
