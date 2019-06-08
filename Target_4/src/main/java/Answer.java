public class Answer {
    private static String dist = "distance"; // (Число) Расстояние до найденного города, в километрах
    private static String code = "code"; // (Строка) Код города в системе кодирования Яндекс.Расписаний
    private static String title = "title"; // (Строка) Название города
    private static String popular_title = "popular_title"; // (Строка) Общепринятое название города
    private static String short_title = "short_title"; // (Строка) Краткое название города
    private static String lat = "lat"; // (Число) Широта, на которой находится город
    private static String lng = "lng"; // (Число) Долгота, на которой находится город
    private static String type = "type"; // (Строка) Тип транспортного пункта:
        private static String station = "station"; // станция
        private static String settlement = "settlement"; // станция

    public static String getDist() {
        return dist;
    }

    public static String getCode() {
        return code;
    }

    public static String getTitle() {
        return title;
    }

    public static String getPopular_title() {
        return popular_title;
    }

    public static String getShort_title() {
        return short_title;
    }

    public static String getLat() {
        return lat;
    }

    public static String getLng() {
        return lng;
    }

    public static String getType() {
        return type;
    }

    public static String getStation() {
        return station;
    }

    public static String getSettlement() {
        return settlement;
    }
}
