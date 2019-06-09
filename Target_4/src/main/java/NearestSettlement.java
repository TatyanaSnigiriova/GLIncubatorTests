public class NearestSettlement {
    // Поля для запроса
    protected static String localHTTPS = "/nearest_settlement";
    protected static String r_lat = Dict.getLat(); // Широта (об)
    protected static String r_lng = Dict.getLng(); // Долгота (об)

    protected static String r_dist = Dict.getDist(); // Радиус охвата (необ)
    protected static String r_lang = Dict.getLang(); // Язык (необ)
    protected static String r_format = Dict.getFormat(); // Формат (необ)

    // Поля для ответа
    protected static String a_dist = Dict.getDist(); // (Число) Расстояние до найденного города, в километрах
    protected static String a_code = Dict.getCode(); // (Строка) Код города в системе кодирования Яндекс.Расписаний
    protected static String a_title = Dict.getTitle(); // (Строка) Название города
    protected static String a_popular_title = Dict.getPop_title(); // (Строка) Общепринятое название города
    protected static String a_short_title = Dict.getShort_title(); // (Строка) Краткое название города
    protected static String a_lat = Dict.getLat(); // (Число) Широта, на которой находится город
    protected static String a_lng = Dict.getLng(); // (Число) Долгота, на которой находится город
    protected static String a_type = Dict.getType(); // (Строка) Тип транспортного пункта:
        protected static String a_station = Dict.getStation(); // Станция
        protected static String a_settlement = Dict.getSettlement(); // Поселение
}
