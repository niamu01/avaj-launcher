package avaj;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"FOG", "RAIN", "SNOW", "SUN"};
    private WeatherProvider() {};
    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    };
    public String getCurrentWeather(Coordinates coordinates) {
        //change to random value?
        Random random = new Random();

        int x = coordinates.getLongitude();
        int y = coordinates.getLatitude();
        int z = coordinates.getHeight();
        int index = x + y + z + random.nextInt();
        if (index < 0)
            index *= -1;
        return weather[index % 4];
    };
}
