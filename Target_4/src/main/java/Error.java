public class Error {
    // Для HTTP-статусов 400 и 404
    private static String req = "error.request";
    private static String text = "error.text";
    private static String error_code = "error.error_code";
    private static String http_code = "error.http_code";

    public static String getReq() {
        return req;
    }

    public static String getText() {
        return text;
    }

    public static String getError_code() {
        return error_code;
    }

    public static String getHttp_code() {
        return http_code;
    }
}
