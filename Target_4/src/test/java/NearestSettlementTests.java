// https://tech.yandex.ru/rasp/doc/reference/nearest-settlement-docpage/
/*
    Ближайший город:

    Запрос позволяет получить информацию о ближайшем к указанной точке городе.
Точка определяется географическими координатами (широтой и долготой) согласно WGS84.
Поиск можно ограничить определенным радиусом (по умолчанию — 10 километров, но не больше 50).

*/

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class NearestSettlementTests extends NearestSettlement {
    // Обычные тестовые сдучаи:
    //     1) Координаты офиса GoodLine
    @Test
    void req_with_dist50_1() {
        ValidatableResponse req = given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, 55.388645).
                param(r_lng, 86.098903).
                param(r_dist, 50).
                when().
                get(localHTTPS).
                then();

        req.statusCode(200).body(a_title, equalTo("Кемерово"));
        //req.statusCode(200).body(a_lat, equalTo(55.35488));
        //req.statusCode(200).body(a_lng, equalTo(86.08684));
    }

    //      2) Координаты красной площади
    @Test
    void req_without_dist_with_uk_UA() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format,  Config.getJson()).
                param(r_lat, 55.753595).
                param(r_lng, 37.621031).
                param(r_lang, "uk_UA").
                when().
                get(localHTTPS).
                then().
                statusCode(200).
                body(a_title, equalTo("Москва"));
    }

    //      3) 'Удалённые' координаты в Тихом океане
    @Test
    void req_with_dist50_2() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format,  Config.getJson()).
                param(r_lat, 21.222583).
                param(r_lng, -157.751001).
                param(r_dist, 50).
                when().
                get(localHTTPS).
                then().
                statusCode(200).
                body(a_title, equalTo("Гонолулу"));
    }

    // 4) Запрос с нулевым расстоянием
    // 'Точные' координаты Кемерово (по версии Яндекса) (Можно вывести в 1 пункте координаты города)
    //     Выдаёт ошибку 404: "Населенный пункт не найден", хотя этого не должно происходить,
    // если они позволяют пользователю вписывать r_dist = 0
    // Видимо, с погрешностью они не считают, а берут точный радиус области, который передаётся как п-р dist
    @Test
    void req_with_dist0() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format,  Config.getJson()).
                param(r_lat, 55.35488).
                param(r_lng, 86.08684).
                param(r_dist, 0).
                when().
                get(localHTTPS).
                then().
                statusCode(200).
                body(a_popular_title, equalTo("Кемерово"));
    }

    // 5) Запрос с нулевым расстоянием + eps
    // 'Точные' координаты Кемерово (по версии Яндекса) (Можно вывести в 1 пункте координаты города)
    @Test
    void req_with_dist0_eps() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, 55.35488).
                param(r_lng, 86.08684).
                param(r_dist, 0.001).
                when().
                get(localHTTPS).
                then().
                statusCode(200).
                body(a_popular_title, equalTo("Кемерово"));
    }

    // 6) Запрос с единичным расстоянием
    // Точные координаты Кемерово (по версии Яндекса) (Можно вывести в 1 пункте координаты города)
    // А тут проходит без проблем
    @Test
    void req_with_dist1() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format,  Config.getJson()).
                param(r_lat, 55.35488).
                param(r_lng, 86.08684).
                param(r_dist, 1).
                when().
                get(localHTTPS).
                then().
                statusCode(200).
                body(a_short_title, equalTo("Кемерово"));
    }

    // Обработка сообщений об ошибке:
    // 1) Очень 'удалённые' координаты в Тихом океане
    @Test
    void req_with_error404() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, -55.069065).
                param(r_lng, -124.460701).
                param(r_dist, 50).
                when().
                get(localHTTPS).
                then().
                statusCode(404).
                body(Error.getE_text(), equalTo("Населенный пункт не найден"));
    }

    // 2) Запрос без обязательных параметров
    @Test
    void req_with_error400_without_coordinate() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                when().
                get(localHTTPS).
                then().
                statusCode(400).
                body(Error.getE_error_code(), equalTo("v3.0_release-3.105.0_284"));
    }

    // 3) Запрос с 'запредельным' расстоянием 1
    @Test
    void req_with_error400_with_dist51() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, 55.388645).
                param(r_lng, 86.098903).
                param(r_dist, 51).
                when().
                get(localHTTPS).
                then().
                statusCode(400).
                body(Error.getE_text(), equalTo("distance: Параметр distance должен быть числом от 0 до 50, по умолчанию 10"));
    }

    // 4) Запрос с чуть 'запредельным' расстоянием
    @Test
    void req_with_error400_with_dist100() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, 55.388645).
                param(r_lng, 86.098903).
                param(r_dist, 100).
                when().
                get(localHTTPS).
                then().
                statusCode(400).
                body(Error.getE_text(), equalTo("distance: Параметр distance должен быть числом от 0 до 50, по умолчанию 10"));
    }

    // 5) Запрос с отрицательным расстоянием
    @Test
    void req_with_error400_with_dist_10100() {
        given().
                baseUri(Config.getHTTPS()).
                contentType(ContentType.JSON).
                header(Config.getAuth(), Config.getKey()).
                param(r_format, Config.getJson()).
                param(r_lat, 55.388645).
                param(r_lng, 86.098903).
                param(r_dist, -10100).
                when().
                get(localHTTPS).
                then().
                statusCode(400).
                body(Error.getE_text(), equalTo("distance: Параметр distance должен быть числом от 0 до 50, по умолчанию 10"));
    }

}
