import forecast.CurrentWeather;
import forecast.ThreeDayForecast;
import forecast.Weather;

public class Main {

    public static void main(String[] args) {
        /*
        //current weather by console input
        CurrentWeather currentWeatherConsole = CurrentWeather.weatherByConsoleInput();
        System.out.println(currentWeatherConsole.getCurrentTemperatureInCelsius());

        //current weather by parameter
        CurrentWeather currentWeatherParameter = CurrentWeather.weatherByCityName("Tallinn");
        System.out.println(currentWeatherParameter.getCurrentTemperatureInCelsius());


        //three day forecast by console input
        ThreeDayForecast threeDayWeatherConsole = ThreeDayForecast.weatherByConsoleInput();
        System.out.println(threeDayWeatherConsole.getThreeDayForecast());

        //three day forecast by parameter
        ThreeDayForecast threeDayWeatherParameter = ThreeDayForecast.weatherByCityName("Helsinki");
        System.out.println(threeDayWeatherParameter.getThreeDayForecast());*/


        Weather weather = new Weather();
        weather.writeWeather();
    }

}
