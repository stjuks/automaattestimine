package forecast;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WeatherTest {

    @Test
    public void testFahrenheitToCelsiusConversion() {
        double temperatureInFahrenheit = 100;
        double temperatureInCelsius = Weather.fahrenheitToCelsius(temperatureInFahrenheit);
        assertEquals(37.78, temperatureInCelsius);
    }

    @Test
    public void testIfOutputFilesAreCreated() {
        Weather weather = new Weather();
        List<String> locations = weather.readFromFile("src/input.txt");
        weather.writeWeather();
        for (String location : locations) {
            File file = new File("src/output/" + location + ".txt");
            assertTrue(file.exists());
        }
    }

    @Test
    public void testIfLocationsAreReadFromInputFile() {
        Weather mockedWeather = mock(Weather.class);
        mockedWeather.writeWeather();
        verify(mockedWeather).readFromFile("src/input.txt");
    }

}
