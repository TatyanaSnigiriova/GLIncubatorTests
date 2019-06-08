public class NearestStations {
    // Поля для запроса
    protected String localHTTPS = "/nearest_stations";
    protected String r_lat = Request.getLat(); // Широта (об)
    protected String r_lng = Request.getLng(); // Долгота (об)
    protected String r_dist = Request.getDist(); // Радиус охвата (об)
    protected String r_lang = Request.getLang(); // Язык (необ)
    protected String r_offset = Request.getOffset(); // Сдвиг относительно первого рейса в ответе (необ)
    protected String r_limit = Request.getLimit(); // Ограничение на количество рейсов в ответе (необ)
    protected String r_stat_types = Request.getStat_types(); // Тип станции (необ)
    protected String r_trans_types = Request.getTrans_types(); // Тип транспортного средства (необ)
        protected String r_airport = Request.getAirport();
        protected String r_bus_station = Request.getBus_station();
        protected String r_bus_stop = Request.getBus_stop();
    protected String r_format = Request.getFormat(); // Формат (необ)
}
