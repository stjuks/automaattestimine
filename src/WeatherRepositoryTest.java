import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherRepositoryTest {

    @Test
    public void testIfCurrentTemperatureIsDouble() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "Estonia");
            WeatherRepository repository = new WeatherRepository();
            WeatherReport report = repository.getWeather(request);
            double currentTemperature = Double.parseDouble(report.currentTemperature);
        } catch (NumberFormatException e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfCoordinateFormatIsCorrect() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "Estonia");
            WeatherRepository repository = new WeatherRepository();
            WeatherReport report = repository.getWeather(request);
            assertEquals(true, report.coordinates.matches("\\d{3}:\\d{3}"));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void testIfRequestCountryMatchesReportCountry() {
        try {
            WeatherRequest request = new WeatherRequest("Tallinn", "Estonia");
            WeatherRepository repository = new WeatherRepository();
            WeatherReport report = repository.getWeather(request);
            assertEquals(report.country, request.country);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }
}
