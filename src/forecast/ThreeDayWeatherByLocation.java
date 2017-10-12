package forecast;

import net.aksingh.owmjapis.HourlyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ThreeDayWeatherByLocation {

    private static final String API_KEY = "485ef7b3d9a99b50872d90ae3f64b4b4";
    private static final int REPORTS_IN_DAY = 8;
    private HourlyForecast hourlyForecast;

    public ThreeDayWeatherByLocation(String cityName) {
        OpenWeatherMap openWeatherMap = new OpenWeatherMap(API_KEY);
        try {
            this.hourlyForecast = openWeatherMap.hourlyForecastByCityName(cityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> getThreeDayMinMaxTemperatureInCelsius() {
        DecimalFormat df = new DecimalFormat("#.#");
        ArrayList<Double> oneDayTemperatureList = new ArrayList<>();
        ArrayList<Double> threeDayMaxMinTemperatures = new ArrayList<>();

        for (int i = 0; i < REPORTS_IN_DAY * 3; i++) {
            double temperature = hourlyForecast
                    .getForecastInstance(i)
                    .getMainInstance()
                    .getTemperature();
            double temperatureInCelsius = fahrenheitToCelsius(temperature);

            oneDayTemperatureList.add(
                    Double.parseDouble(df.format(temperatureInCelsius)));

            if (oneDayTemperatureList.size() == REPORTS_IN_DAY) {
                threeDayMaxMinTemperatures.add
                        (Collections.max(oneDayTemperatureList));
                threeDayMaxMinTemperatures.add
                        (Collections.min(oneDayTemperatureList));
                oneDayTemperatureList = new ArrayList<>();
            }
        }
        return threeDayMaxMinTemperatures;
    }

    public String formatThreeDayForecast() {
        ArrayList<Double> forecastList = getThreeDayMinMaxTemperatureInCelsius();
        return "First day max/min temperature: " + forecastList.get(0) + "/" + forecastList.get(1) + "\n"
                + "Second day max/min temperature: " + forecastList.get(2) + "/" + forecastList.get(3) + "\n"
                + "Third day max/min temperature: " + forecastList.get(4) + "/" + forecastList.get(5);
    }

    private double fahrenheitToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    public String getLocation() {
        return hourlyForecast.getCityInstance().getCityName();
    }

    public String getLocationCoordinates() {
        DecimalFormat formatCoords = new DecimalFormat("#.####");
        formatCoords.setMinimumFractionDigits(4);
        double latitude = getLatitude();
        double longitude = getLongitude();
        return "Latitude: " + formatCoords.format(latitude)
                + ", Longitude: " + formatCoords.format(longitude);
    }

    public double getLatitude() {
        return hourlyForecast.getCityInstance().getCoordInstance().getLatitude();
    }

    public double getLongitude() {
        return hourlyForecast.getCityInstance().getCoordInstance().getLongitude();
    }
}
