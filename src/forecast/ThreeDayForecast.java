package forecast;

import net.aksingh.owmjapis.HourlyForecast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ThreeDayForecast extends Weather {

    private static final int REPORTS_IN_DAY = 8;
    private HourlyForecast hourlyForecast;
    private String location;

    public static ThreeDayForecast weatherByCityName(String cityName) {
        return new ThreeDayForecast(cityName);
    }

    public static ThreeDayForecast weatherByConsoleInput() {
        System.out.println("Enter city name for 3 day forecast: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new ThreeDayForecast(input);
    }

    private ThreeDayForecast(String cityName) {
        try {
            this.hourlyForecast = openWeatherMap.hourlyForecastByCityName(cityName);
            this.location = hourlyForecast.getCityInstance().getCityName();
        } catch (Exception e) {
            System.out.println(noCityException());
        }
    }

    public String getThreeDayForecast() {
        try {
            ArrayList<Double> forecastList = getThreeDayMinMaxTemperatureInCelsius();
            return "1st day max/min temperature - " + forecastList.get(0) + "/" + forecastList.get(1) + "C\n"
                    + "2nd day max/min temperature - " + forecastList.get(2) + "/" + forecastList.get(3) + "C\n"
                    + "3rd day max/min temperature - " + forecastList.get(4) + "/" + forecastList.get(5) + "C";
        } catch (Exception e) {
            return noCityException();
        }
    }

    public String getLocationCoordinates() {
        try {
            DecimalFormat formatCoords = new DecimalFormat("#.####");
            formatCoords.setMinimumFractionDigits(4);
            double latitude = hourlyForecast.getCityInstance().getCoordInstance().getLatitude();
            double longitude = hourlyForecast.getCityInstance().getCoordInstance().getLongitude();
            return formatCoords.format(latitude)
                    + ", " + formatCoords.format(longitude);
        } catch (Exception e) {
            return noCityException();
        }
    }

    public ArrayList<Double> getThreeDayMinMaxTemperatureInCelsius() {
        ArrayList<Double> oneDayTemperatureList = new ArrayList<>();
        ArrayList<Double> threeDayMaxMinTemperatures = new ArrayList<>();

        for (int i = 0; i < REPORTS_IN_DAY * 3; i++) {
            double temperature = hourlyForecast
                    .getForecastInstance(i)
                    .getMainInstance()
                    .getTemperature();
            double temperatureInCelsius = fahrenheitToCelsius(temperature);

            oneDayTemperatureList.add
                    (Double.parseDouble(String.format("%.1f", temperatureInCelsius)));

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

    public String getLocation() {
        return location;
    }

}
