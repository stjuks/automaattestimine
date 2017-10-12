import forecast.CurrentWeatherByLocation;
import forecast.ThreeDayWeatherByLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherForecastTest {

    private CurrentWeatherByLocation currentWeather;
    private ThreeDayWeatherByLocation threeDayWeather;

    @BeforeEach
    public void setupBeforeEachTest() {
        currentWeather = new CurrentWeatherByLocation("Tallinn");
        threeDayWeather = new ThreeDayWeatherByLocation("Tallinn");
    }

    @Test
    public void testIfThreeDayForecastTemperatureListLengthIs6() {
        assertEquals(6, threeDayWeather.getThreeDayMinMaxTemperatureInCelsius().size());
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
        String temperature = Double.toString(currentWeather.getCurrentTemperatureInCelsius());
        assertTrue(temperature.matches("^[0-9]*\\.[0-9]$"));
    }

    @Test
    public void testIfLongitudeHasFourDecimalPlaces() {
        String longitude = Double.toString(currentWeather.getLongitude());
        assertTrue(longitude.matches("^[0-9]*\\.[0-9]$"));
    }

    @Test
    public void testFahrenheitToCelsiusConversion() {
        double temperatureInFahrenheit = currentWeather.getCurrentTemperatureInFahrenheit();
        double temperatureInCelsius = (temperatureInFahrenheit - 32) / 1.8;
        assertEquals(temperatureInCelsius, currentWeather.getCurrentTemperatureInCelsius());
    }

}
