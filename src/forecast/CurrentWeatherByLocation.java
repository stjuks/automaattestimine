package forecast;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import java.text.DecimalFormat;

public class CurrentWeatherByLocation {

    private static final String API_KEY = "485ef7b3d9a99b50872d90ae3f64b4b4";
    private CurrentWeather currentWeather;
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public CurrentWeatherByLocation(String cityName) {
        OpenWeatherMap openWeatherMap = new OpenWeatherMap(API_KEY);
        try {
            this.currentWeather = openWeatherMap.currentWeatherByCityName(cityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCurrentTemperatureInCelsius() {
        double temperature = currentWeather.getMainInstance().getTemperature();
        double temperatureInCelsius = fahrenheitToCelsius(temperature);
        return Double.parseDouble(decimalFormat.format(temperatureInCelsius));
    }

    public double getCurrentTemperatureInFahrenheit() {
        double temperature = currentWeather.getMainInstance().getTemperature();
        return Double.parseDouble(decimalFormat.format(temperature));
    }

    public String getLocation() {
        return currentWeather.getCityName();
    }

    private double fahrenheitToCelsius(double temp) {
        return (temp - 32) / 1.8;
    }

    public String getLocationCoordinates() {
        DecimalFormat formatCoords = new DecimalFormat("#.####");
        formatCoords.setMinimumFractionDigits(4);
        return "Latitude: " + formatCoords.format(getLatitude())
                + ", Longitude: " + formatCoords.format(getLongitude());
    }

    public double getLatitude() {
        return currentWeather.getCoordInstance().getLatitude();
    }

    public double getLongitude() {
        return currentWeather.getCoordInstance().getLongitude();
    }

}
