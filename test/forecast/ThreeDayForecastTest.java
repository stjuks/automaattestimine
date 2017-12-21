package forecast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreeDayForecastTest {

    private ThreeDayForecast threeDayForecast;

    @BeforeEach
    public void setupBeforeEachTest() {
        threeDayForecast = ThreeDayForecast.weatherByCityName("Tallinn");
    }

    @Test
    public void testIfForecastFormatIsCorrect() {
        String format = "(1st day max/min temperature - )(-?[0-9]*\\.[0-9])(/)(-?[0-9]*\\.[0-9])(C\n)"
                + "(2nd day max/min temperature - )(-?[0-9]*\\.[0-9])(/)(-?[0-9]*\\.[0-9])(C\n)"
                + "(3rd day max/min temperature - )(-?[0-9]*\\.[0-9])(/)(-?[0-9]*\\.[0-9])(C)";
        String forecast = threeDayForecast.getThreeDayForecast();
        assertTrue(forecast.matches(format));
    }

    @Test
    public void testErrorIfLocationIsNull() {
        ThreeDayForecast threeDayWeather = ThreeDayForecast.weatherByCityName(null);
        assertEquals(threeDayWeather.noCityException(), threeDayWeather.getThreeDayForecast());
    }

    @Test
    public void testErrorIfLocationIsWrong() {
        ThreeDayForecast threeDayWeather = ThreeDayForecast.weatherByCityName("asdjaskd");
        assertEquals(threeDayWeather.noCityException(), threeDayWeather.getThreeDayForecast());
    }

    @Test
    public void testIfCoordinatesHave4DecimalPlaces() {
        String actualCoordinates = threeDayForecast.getLocationCoordinates();
        String expectedFormat = "(-?[0-9]*\\.[0-9]{4})(, )(-?[0-9]*\\.[0-9]{4})";
        assertTrue(actualCoordinates.matches(expectedFormat));
    }

    @Test
    public void testIfFirstDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(0);
        double minTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(1);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfSecondDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(2);
        double minTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(3);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfThirdDayMaxTempIsHigherThanMinTemp() {
        double maxTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(4);
        double minTemp = threeDayForecast.getThreeDayMinMaxTemperatureInCelsius().get(5);
        assertTrue(maxTemp > minTemp);
    }

    @Test
    public void testIfReturnsCorrectLocation() {
        assertEquals("Tallinn", threeDayForecast.getLocation());
    }

}
