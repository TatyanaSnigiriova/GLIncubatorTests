import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NearestStationsTest1 extends NearestStations{
    // Вариант с одним запросом и множеством тестов для этого запроса

    private static StringBuffer responseContent;
    private static HttpURLConnection connection;
    private static int status;

    @BeforeAll
    public static void getAnswer() {
        BufferedReader reader;
        String line;
        responseContent = new StringBuffer();
        try {
            URL url = new URL(Config.getHTTPS() + localHTTPS + "/?" +
                    "apikey=" + Config.getKey() +
                    "&" + r_format + "=" + Config.getJson() +
                    "&" + r_lat + "=" + 55.388645 +
                    "&" + r_lng + "=" + 86.098903 +
                    "&" + r_dist + "=" + 15 +
                    "&" + r_station_types + "=" + r_airport);
            connection = (HttpURLConnection) url.openConnection();
            status = connection.getResponseCode();
            if (status != 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    // Можно было бы проверить все поля, но...
    @Test
    public void check_total() {
        Assertions.assertEquals( status, 200);
        JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
        JSONObject pagination = json.getJSONObject(a_pagination);
        Assertions.assertEquals(pagination.getInt(a_total), 1);
    }

    @Test
    public void check_station_type() {
        Assertions.assertEquals( status, 200);
        JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
        JSONObject stations = json.getJSONArray(a_stations).getJSONObject(0);
        Assertions.assertEquals(stations.getString(a_station_type), a_airport );
    }

    // Проверка на возвращаемые названия станции
    @Test
    public void check_titles() {
        Assertions.assertEquals( status, 200);
        JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
        JSONObject stations = json.getJSONArray(a_stations).getJSONObject(0);
        Assertions.assertEquals(stations.getString(a_title), "Кемерово");
        Assertions.assertEquals(stations.getString(a_popular_title), "Кемерово");
        Assertions.assertEquals(stations.getString(a_short_title), "Кемерово");
    }

    @Test
    public void check_transport_type() {
        Assertions.assertEquals( status, 200);
        JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
        JSONObject stations = json.getJSONArray(a_stations).getJSONObject(0);
        Assertions.assertEquals(stations.getString(a_transport_type), a_plane);
    }

    @Test
    public void check_type() {
        Assertions.assertEquals( status, 200);
        JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
        JSONObject stations = json.getJSONArray(a_stations).getJSONObject(0);
        Assertions.assertEquals(stations.getString(a_type), a_station);
    }
}
