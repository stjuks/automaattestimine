import forecast.CurrentWeatherByLocation;
import forecast.ThreeDayWeatherByLocation;
import forecast.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherForecastTest {

    private CurrentWeatherByLocation currentWeather;
    private ThreeDayWeatherByLocation threeDayWeather;

    @BeforeEach
    public void setupBeforeEachTest() {
        currentWeather = CurrentWeatherByLocation.weatherByCityName("Tallinn");
        threeDayWeather = ThreeDayWeatherByLocation.weatherByCityName("Tallinn");
    }

    @Test
    public void testIfThreeDayWeatherForecastFormatIsCorrect() {
        String format = "(1st day max/min temperature - )([0-9]*\\.[0-9])(/)([0-9]*\\.[0-9])"
                + "(; 2nd day max/min temperature - )([0-9]*\\.[0-9])(/)([0-9]*\\.[0-9])"
                + "(; 3rd day max/min temperature - )([0-9]*\\.[0-9])(/)([0-9]*\\.[0-9])";
        assertTrue(threeDayWeather.getThreeDayForecast().matches(format));
    }

    @Test
    public void testIfCurrentWeatherReturnsCorrectLocation() {
        assertEquals("Tallinn", currentWeather.getLocation());
    }

    @Test
    public void testIfThreeDayForecastReturnsCorrectLocation() {
        assertEquals("Tallinn", threeDayWeather.getLocation());
    }

    @Test
    public void testIfFirstDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(0);
        double minTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(1);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfSecondDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(2);
        double minTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(3);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfThirdDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(4);
        double minTemp = threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().get(5);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfCurrentTemperatureHasOneDecimalPlace() {
        String temperature = currentWeather.getCurrentTemperatureInCelsius();
        assertTrue(temperature.matches("^[0-9]*\\.[0-9]$"));
    }

    @Test
    public void testFahrenheitToCelsiusConversion() {
        double temperatureInFahrenheit = Double.parseDouble(currentWeather.getCurrentTemperatureInFahrenheit());
        double temperatureInCelsius = Double.parseDouble(String.format("%.1f", (temperatureInFahrenheit - 32) / 1.8));
        double actualTemperatureInCelsius = Double.parseDouble(currentWeather.getCurrentTemperatureInCelsius());
        assertEquals(temperatureInCelsius, actualTemperatureInCelsius);
    }

    @Test
    public void testIfCurrentWeatherCoordinatesHave4DecimalPlaces() {
        String actualCoordinates = currentWeather.getLocationCoordinates();
        String expectedFormat = "(Latitude: )([0-9]*\\.[0-9]{4})(, Longitude: )([0-9]*\\.[0-9]{4})";
        assertTrue(actualCoordinates.matches(expectedFormat));
    }

    @Test
    public void testIfThreeDayForecastCoordinatesHave4DecimalPlaces() {
        String actualCoordinates = threeDayWeather.getLocationCoordinates();
        String expectedFormat = "(Latitude: )([0-9]*\\.[0-9]{4})(, Longitude: )([0-9]*\\.[0-9]{4})";
        assertTrue(actualCoordinates.matches(expectedFormat));
    }

    @Test
    public void testIfCurrentWeatherResultIsWrittenToOutputFile() {
        String result = currentWeather.getCurrentTemperatureInCelsius();
        String output = Weather.readFromFile("src/output.txt");
        assertEquals(result, output);
    }

    @Test
    public void testIfThreeDayForecastResultIsWrittenToOutputFile() {
        String result = threeDayWeather.getThreeDayForecast();
        String output = Weather.readFromFile("src/output.txt");
        assertEquals(result, output);
    }

    @Test
    public void testIfCurrentWeatherReadsLocationFromFile() {
        CurrentWeatherByLocation currentWeatherFromFile = CurrentWeatherByLocation.weatherByFileInput();
        String expectedLocation = Weather.readFromFile("src/input.txt");
        String actualLocation = currentWeatherFromFile.getLocation();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testIfThreeDayForecastReadsLocationFromFile() {
        ThreeDayWeatherByLocation threeDayWeatherFromFile = ThreeDayWeatherByLocation.weatherByFileInput();
        String expectedLocation = Weather.readFromFile("src/input.txt");
        String actualLocation = threeDayWeatherFromFile.getLocation();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testIfCurrentWeatherLocationIsNull() {
        CurrentWeatherByLocation currentWeather = CurrentWeatherByLocation.weatherByCityName(null);
        assertEquals(currentWeather.noCityException(), currentWeather.getCurrentTemperatureInCelsius());
    }

    @Test
    public void testIfThreeDayForecastLocationIsNull() {
        ThreeDayWeatherByLocation threeDayWeather = ThreeDayWeatherByLocation.weatherByCityName(null);
        assertEquals(threeDayWeather.noCityException(), threeDayWeather.getThreeDayForecast());
    }

    @Test
    public void testIfCurrentWeatherLocationIsWrong() {
        CurrentWeatherByLocation currentWeather = CurrentWeatherByLocation.weatherByCityName("alksjd");
        assertEquals(currentWeather.noCityException(), currentWeather.getCurrentTemperatureInCelsius());
    }

    @Test
    public void testIfThreeDayForecastLocationIsWrong() {
        ThreeDayWeatherByLocation threeDayWeather = ThreeDayWeatherByLocation.weatherByCityName("asdjaskd");
        assertEquals(threeDayWeather.noCityException(), threeDayWeather.getThreeDayForecast());
    }
}
