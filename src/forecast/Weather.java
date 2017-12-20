package forecast;

import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Weather {

    private static final String API_KEY = "485ef7b3d9a99b50872d90ae3f64b4b4";
    OpenWeatherMap openWeatherMap = new OpenWeatherMap(API_KEY);

    private void writeWeatherFile(String location) {
        CurrentWeather currentWeather = CurrentWeather.weatherByCityName(location);
        ThreeDayForecast threeDayForecast = ThreeDayForecast.weatherByCityName(location);
        String output = "Weather for " + location + "\n"
                + "Coordinates: " + currentWeather.getLocationCoordinates() + "\n\n"
                + "3 day forecast: " + "\n"
                + threeDayForecast.getThreeDayForecast() + "\n\n"
                + "Current weather: " + "\n"
                + currentWeather.getCurrentTemperatureInCelsius() + "\n";
        writeOutputToFile(output, "src/output/" + location + ".txt");
    }

    public void writeWeather() {
        List<String> inputs = readFromFile("src/input.txt");
        for (String input : inputs) {
            writeWeatherFile(input);
        }
    }

    static double fahrenheitToCelsius(double temp) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return Double.parseDouble(formatter.format((temp - 32) / 1.8));
    }

    String noCityException() {
        return "No such city in database!";
    }

    List<String> readFromFile(String path) {
        String location;
        List<String> locations = new ArrayList<>();
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((location = bufferedReader.readLine()) != null) {
                locations.add(location);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    void writeOutputToFile(String output, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(output);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
