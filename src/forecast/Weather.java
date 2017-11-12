package forecast;

import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.*;

public class Weather {

    private BufferedWriter bufferedWriter;
    private static final String API_KEY = "485ef7b3d9a99b50872d90ae3f64b4b4";
    OpenWeatherMap openWeatherMap = new OpenWeatherMap(API_KEY);

    Weather() {
        try {
            FileWriter writer = new FileWriter("src/output.txt");
            this.bufferedWriter = new BufferedWriter(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double fahrenheitToCelsius(double temp) {
        return (temp - 32) / 1.8;
    }

    public String noCityException() {
        return "No such city in database!";
    }

    public static String readFromFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    void writeOutputToFile(String output) {
        try {
            bufferedWriter.write(output);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
