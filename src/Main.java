import forecast.CurrentWeatherByLocation;
import forecast.ThreeDayWeatherByLocation;

public class Main {

    public static void main(String[] args) {
        //current weather by console input
        CurrentWeatherByLocation currentWeatherConsole = CurrentWeatherByLocation.weatherByConsoleInput();
        System.out.println(currentWeatherConsole.getCurrentTemperatureInCelsius());

        //current weather by parameter
        CurrentWeatherByLocation currentWeatherParameter = CurrentWeatherByLocation.weatherByCityName("Tallinn");
        System.out.println(currentWeatherParameter.getCurrentTemperatureInCelsius());

        //current weather from file
        CurrentWeatherByLocation currentWeatherFile = CurrentWeatherByLocation.weatherByFileInput();
        System.out.println(currentWeatherFile.getCurrentTemperatureInCelsius());

        //three day forecast by console input
        ThreeDayWeatherByLocation threeDayWeatherConsole = ThreeDayWeatherByLocation.weatherByConsoleInput();
        System.out.println(threeDayWeatherConsole.getThreeDayForecast());

        //three day forecast by parameter
        ThreeDayWeatherByLocation threeDayWeatherParameter = ThreeDayWeatherByLocation.weatherByCityName("Helsinki");
        System.out.println(threeDayWeatherParameter.getThreeDayForecast());

        //three day forecast from file
        ThreeDayWeatherByLocation threeDayWeatherFile = ThreeDayWeatherByLocation.weatherByFileInput();
        System.out.println(threeDayWeatherFile.getThreeDayForecast());
    }

}
