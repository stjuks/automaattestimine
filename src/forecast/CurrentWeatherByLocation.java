package forecast;

import net.aksingh.owmjapis.CurrentWeather;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrentWeatherByLocation extends Weather {

    private CurrentWeather currentWeather;
    private String location;

    public static CurrentWeatherByLocation weatherByCityName(String cityName) {
        return new CurrentWeatherByLocation(cityName);
    }

    public static CurrentWeatherByLocation weatherByConsoleInput() {
        System.out.println("Enter city name for current weather: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new CurrentWeatherByLocation(input);
    }

    public static CurrentWeatherByLocation weatherByFileInput() {
        String cityName = readFromFile("src/input.txt");
        return new CurrentWeatherByLocation(cityName);
    }

    private CurrentWeatherByLocation(String cityName) {
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
            String formattedTemperature = String.format("%.1f", temperatureInCelsius);
            writeOutputToFile(formattedTemperature);
            return formattedTemperature;
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getCurrentTemperatureInFahrenheit() {
        try {
            double temperature = currentWeather.getMainInstance().getTemperature();
            String formattedTemperature = String.format("%.1f", temperature);
            writeOutputToFile(formattedTemperature);
            return formattedTemperature;
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
            return "Latitude: " + formatCoords.format(latitude)
                    + ", Longitude: " + formatCoords.format(longitude);
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getLocation() {
        return location;
    }

}
