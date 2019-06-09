import io.restassured.internal.assertion.Assertion;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NearestStationsTests extends NearestStations{
    // Вариант, где каждый тест представляет собой запрос.

    @Test
    public void req_with_multiple_param()
    {
        HttpURLConnection connection;
        try {
            URL url = new URL(Config.getHTTPS() + localHTTPS + "/?" +
                    "apikey=" + Config.getKey() +
                    "&" + r_format + "=" + Config.getJson() +
                    "&" + r_lat + "=" + 55.388645 +
                    "&" + r_lng + "=" + 86.098903 +
                    "&" + r_dist + "=" + 20 +
                    "&" + r_station_types + "=" + r_bus_station + "," +r_train_station + "," +r_airport);
            connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            Assertions.assertEquals(status, 200);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer responseContent = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            connection.disconnect();

            JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
            JSONObject pagination = json.getJSONObject(a_pagination);
            JSONArray stations = json.getJSONArray(a_stations);

            // Проверим, что вернулось 3 значения и первые два имеют 'station_type_name' = "автовокзал"
            Assertions.assertEquals(pagination.getInt(a_total), 3);
            for (int i = 0; i < 2; i++)
                Assertions.assertEquals(stations.getJSONObject(i).getString("station_type_name"), "автовокзал");
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void req_with_only_required_params()
    {
        HttpURLConnection connection;
        try {
            URL url = new URL(Config.getHTTPS() + localHTTPS + "/?" +
                    "apikey=" + Config.getKey() +
                    "&" + r_format + "=" + Config.getJson() +
                    "&" + r_lat + "=" + 55.388645 +
                    "&" + r_lng + "=" + 86.098903 +
                    "&" + r_dist + "=" + 50 );
            connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            Assertions.assertEquals(status, 200);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer responseContent = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            connection.disconnect();

            JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
            JSONObject pagination = json.getJSONObject(a_pagination);

            // Проверим, что вернулось 40 значений
            Assertions.assertEquals(pagination.getInt(a_total), 40);
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void req_with_error400()
    {
        HttpURLConnection connection;
        try {
            URL url = new URL(Config.getHTTPS() + localHTTPS + "/?" +
                    "apikey=" + Config.getKey() +
                    "&" + r_format + "=" + Config.getJson() +
                    "&" + r_lat + "=" + 55.388645 +
                    "&" + r_lng + "=" + 86.098903 +
                    "&" + r_dist + "=" + 100 );
            connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            Assertions.assertEquals(status, 400);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String line;
            StringBuffer responseContent = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            connection.disconnect();

            JSONObject json = new JSONArray("[" + responseContent.toString() + "]").getJSONObject(0);
            Assertions.assertEquals(json.getJSONObject(Error.getError()).getString(Error.getText()), "distance: Параметр distance должен быть числом от 0 до 50, по умолчанию 10");


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
