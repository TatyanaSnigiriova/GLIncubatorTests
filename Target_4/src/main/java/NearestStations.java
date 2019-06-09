public class NearestStations {
    // Поля для запроса
    protected static String localHTTPS = "/nearest_stations";
    protected static String r_lat = Dict.getLat(); // Широта (об)
    protected static String r_lng = Dict.getLng(); // Долгота (об)
    protected static String r_dist = Dict.getDist(); // Радиус, в котором следует искать станции, в километрах (об)

    protected static String r_lang = Dict.getLang(); // Язык (необ)
    protected static String r_format = Dict.getFormat(); // Формат (необ)
    protected static String r_stat_types = Dict.getStat_types(); // Тип станции (необ)
    protected static String r_station_types = Dict.getStat_types(); // Типы запрашиваемых станций (несколько типов можно перечислить через запятую) (необ)
        protected static String r_airport = Dict.getAirport();
        protected static String r_bus_station = Dict.getBus_station();
        protected static String r_bus_stop = Dict.getBus_stop();
        protected static String r_train_station = Dict.getTrain_station();
    protected static String r_transport_types = Dict.getTrans_types(); // Типы транспортного средства, для которых нужно искать станции (несколько типов одновременно можно указать через запятую) (необ)
        protected static String r_plane = Dict.getPlane();
        protected static String r_train = Dict.getTrain();
        protected static String r_suburban = Dict.getSuburban(); // Электричка
        protected static String r_bus = Dict.getBus();
    protected static String r_offset = Dict.getOffset(); // Смещение относительно первого результата поиска. Например, если вам не нужны первые 10 результатов поиска, задайте для параметра значение «10».
                                                        // Значение по умолчанию — 0. (необ)
    protected static String r_limit = Dict.getLimit(); // Ограничение на количество рейсов в ответе (необ)

    // Поля для ответа
    protected static String a_pagination = Dict.getPagination(); // (Массив) Информация о постраничном выводе
        protected static String a_total = Dict.getTotal(); // (Число) Общее количество станций, удовлетворяющих условиям поиска.
        protected static String a_limit = Dict.getLimit(); // (Число) Ограничение на количество станций, которые Яндекс.Расписания возвращают в ответ на запрос.
                                                             // Значение по умолчанию — 100.
        protected static String a_offset = Dict.getOffset(); // (Число) Смещение относительно первого результата поиска. Смещение можно задать в параметре offset.
                                                               // Значение по умолчанию — 0.
    protected static String a_stations = Dict.getStations(); // (Массив) Список станций
    protected static String a_dist = Dict.getDist(); // Расстояние от указанной в запросе точки до полученной в ответе станции
    protected static String a_code = Dict.getCode(); // (Строка) Код станции в системе кодирования Яндекс.Расписаний
    protected static String a_station_type = Dict.getStat_type(); // (Строка) Тип станции
        protected static String a_airport = Dict.getAirport();
        protected static String a_bus_station = Dict.getBus_station();
        protected static String a_bus_stop = Dict.getBus_stop();
        protected static String a_train_station = Dict.getTrain_station();

    protected static String a_station_type_name = Dict.getStation_type_name(); // (Строка) Название типа станции, зависит от языка ответа. Возможные значения на русском — в списке значений ключа station_type.
    protected static String a_type_choices = Dict.getType_choices(); // (Объект) Типы расписаний, доступные для станции. Каждый тип описывается в отдельном объекте, который содержит ссылки на мобильную и десктопную версию расписания.
    protected static String a_schedule = Dict.getSchedule(); // Вид расписания по умолчанию
        protected static String a_tablo = Dict.getTablo(); // Табло аэропорта
        protected static String a_train = Dict.getTrain(); // Расписание железнодорожного вокзала
        protected static String a_suburban = Dict.getSuburban(); // Расписание электричек
        protected static String a_aeroex = Dict.getAeroex(); // Расписание аэроэкспрессов
    protected static String a_title = Dict.getTitle(); // (Строка) Название станции
    protected static String a_popular_title = Dict.getPop_title(); // (Строка) Общепринятое название станции
    protected static String a_short_title = Dict.getShort_title(); // (Строка) Короткое название станции
    protected static String a_majority = Dict.getMajority(); // (Строка) Целое число, определяющее относительную важность станции в транспортном сообщении региона, где 1 — высшая важность (например, главный вокзал города).
    protected static String a_transport_type = Dict.getTrans_type(); // (Строка) Основной тип транспорта для данной станции
        protected static String a_plane = Dict.getPlane();
        protected static String a_bus = Dict.getBus();
    protected static String a_lat = Dict.getLat(); // (Число) Широта
    protected static String a_lng = Dict.getLng(); // (Число) Долгота
    protected static String a_type = Dict.getType(); // (Строка) Вид найденного пункта
        protected static String  a_station = Dict.getStation();
        protected static String a_settlement = Dict.getSettlement();
}
