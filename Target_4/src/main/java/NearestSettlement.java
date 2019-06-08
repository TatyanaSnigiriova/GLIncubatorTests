public class NearestSettlement {
    // Поля для запроса
    protected String localHTTPS = "/nearest_settlement";
    protected String r_lat = Request.getLat(); // Широта (об)
    protected String r_lng = Request.getLng(); // Долгота (об)
    protected String r_dist = Request.getDist(); // Радиус охвата (необ)
    protected String r_lang = Request.getLang(); // Язык (необ)
    protected String r_format = Request.getFormat(); // Формат (необ)

    // Поля для ответа
    protected String a_dist = Answer.getDist(); // (Число) Расстояние до найденного города, в километрах
    protected String a_code = Answer.getCode(); // (Строка) Код города в системе кодирования Яндекс.Расписаний
    protected String a_title = Answer.getTitle(); // (Строка) Название города
    protected String a_popular_title = Answer.getPopular_title(); // (Строка) Общепринятое название города
    protected String a_short_title = Answer.getShort_title(); // (Строка) Краткое название города
    protected String a_lat = Answer.getLat(); // (Число) Широта, на которой находится город
    protected String a_lng = Answer.getLng(); // (Число) Долгота, на которой находится город
    protected String a_type = Answer.getType(); // (Строка) Тип транспортного пункта:
        protected String a_station = Answer.getStation(); // станция
        protected String a_settlement = Answer.getSettlement(); // станция
}
