public class WeatherRepository {

    public WeatherReport getWeather(WeatherRequest request) {
        return new WeatherReport(request.getCity(), request.getCountry());
    }

}
