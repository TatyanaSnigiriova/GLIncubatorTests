public class Request {
    private static String lat = "lat"; // Широта
    private static String lng = "lng"; // Долгота
    private static String dist = "distance"; // Радиус охвата
    private static String lang = "lang"; // Язык
    private static String offset = "offset"; // Сдвиг относительно первого рейса в ответе
    private static String limit = "limit"; // Ограничение на количество рейсов в ответе
    private static String stat_types = "station_types"; // Тип станции
    private static String trans_types = "transport_types"; // Тип транспортного средства
        private static String bus_stop = "bus_stop"; //  Автобусная остановка
        private static String bus_station = "bus_station"; //  Автовокзал остановка
        private static String airport = "airport"; //  Автобусная остановка
    private static String format = "format"; // Формат

    public static String getLat() {
        return lat;
    }

    public static String getLng() {
        return lng;
    }

    public static String getDist() {
        return dist;
    }

    public static String getLang() {
        return lang;
    }

    public static String getOffset() {
        return offset;
    }

    public static String getLimit() {
        return limit;
    }

    public static String getStat_types() {
        return stat_types;
    }

    public static String getTrans_types() {
        return trans_types;
    }

    public static String getBus_stop() {
        return bus_stop;
    }

    public static String getBus_station() {
        return bus_station;
    }

    public static String getAirport() {
        return airport;
    }

    public static String getFormat() {
        return format;
    }
}
