public class Error {
    // Для HTTP-статусов 400 и 404
    private static String error = "error";
    private static String req = "request";
    private static String text = "text";
    private static String error_code = "error_code";
    private static String http_code = "http_code";

    public static String getE_req() {
        return error+"."+req;
    }

    public static String getE_text() {
        return error+"."+text;
    }

    public static String getE_error_code() {
        return error+"."+error_code;
    }

    public static String getE_http_code() {
        return error+"."+http_code;
    }

    public static String getError() {
        return error;
    }

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
