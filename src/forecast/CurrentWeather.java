package forecast;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrentWeather extends Weather {

    private net.aksingh.owmjapis.CurrentWeather currentWeather;
    private String location;

    public static CurrentWeather weatherByCityName(String cityName) {
        return new CurrentWeather(cityName);
    }

    public static CurrentWeather weatherByConsoleInput() {
        System.out.println("Enter city name for current weather: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new CurrentWeather(input);
    }

    private CurrentWeather(String cityName) {
        try {
            this.currentWeather = openWeatherMap.currentWeatherByCityName(cityName);
            this.location = currentWeather.getCityName();
        } catch (Exception e) {
            System.out.println(noCityException());
        }
    }

    public String getCurrentTemperatureInCelsius() {
        try {
            double temperature = currentWeather.getMainInstance().getTemperature();
            double temperatureInCelsius = fahrenheitToCelsius(temperature);
            return String.format("%.1f", temperatureInCelsius) + "C";
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getCurrentTemperatureInFahrenheit() {
        try {
            double temperature = currentWeather.getMainInstance().getTemperature();
            return String.format("%.1f", temperature);
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getLocationCoordinates() {
        try {
            DecimalFormat formatCoords = new DecimalFormat("#.####");
            double latitude = currentWeather.getCoordInstance().getLatitude();
            double longitude = currentWeather.getCoordInstance().getLongitude();
            formatCoords.setMinimumFractionDigits(4);
            return formatCoords.format(latitude)
                    + ", " + formatCoords.format(longitude);
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getLocation() {
        return location;
    }

}
