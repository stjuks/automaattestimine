package forecast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrentWeatherTest {

    private CurrentWeather currentWeather;

    @BeforeEach
    public void setupBeforeEachTest() {
        currentWeather = CurrentWeather.weatherByCityName("Tallinn");
    }

    @Test
    public void testIfReturnsCorrectLocation() {
        assertEquals("Tallinn", currentWeather.getLocation());
    }

    @Test
    public void testIfTemperatureHasOneDecimalPlace() {
        String temperature = currentWeather.getCurrentTemperatureInCelsius();
        assertTrue(temperature.matches("-?[0-9]*\\.[0-9]C"));
    }

    @Test
    public void testIfCoordinatesHave4DecimalPlaces() {
        String actualCoordinates = currentWeather.getLocationCoordinates();
        String expectedFormat = "(^-?[0-9]*\\.[0-9]{4})(, )(-?[0-9]*\\.[0-9]{4})";
        assertTrue(actualCoordinates.matches(expectedFormat));
    }

    @Test
    public void testIfCurrentWeatherLocationIsNull() {
        CurrentWeather currentWeather = CurrentWeather.weatherByCityName(null);
        assertEquals(currentWeather.noCityException(), currentWeather.getCurrentTemperatureInCelsius());
    }

    @Test
    public void testIfCurrentWeatherLocationIsWrong() {
        CurrentWeather currentWeather = CurrentWeather.weatherByCityName("alksjd");
        assertEquals(currentWeather.noCityException(), currentWeather.getCurrentTemperatureInCelsius());
    }

}
