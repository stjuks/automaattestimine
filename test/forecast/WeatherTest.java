package forecast;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        List<String> locations = Weather.readFromFile("src/input.txt");
        weather.writeWeather();
        for (String location : locations) {
            File file = new File("src/output/" + location + ".txt");
            assertTrue(file.exists());
        }
    }

}
