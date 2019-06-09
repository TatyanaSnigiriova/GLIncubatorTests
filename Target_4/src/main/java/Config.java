public class Config {

    private static final String globalHTTPS = "https://api.rasp.yandex.net/v3.0";
    private static final String key = "bbb3561a-d983-49b7-901c-2d0a343d9b76";
    private static final String json = "json";
    private static final String xml = "xml";
    private static final String auth = "Authorization";

    public static String getHTTPS() {
        return globalHTTPS;
    }

    public static String getKey() {
        return key;
    }

    public static String getJson() {
        return json;
    }

    public static String getXml() {
        return xml;
    }

    public static String getAuth() {
        return auth;
    }
}
