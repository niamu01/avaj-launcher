package avaj;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String[] weather = {"FOG", "RAIN", "SNOW", "SUN"};
    private WeatherProvider() {};
    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    };
    public String getCurrentWeather(Coordinates coordinates) {
        //change to random value?
        int value = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        return weather[value % 4];
    };
}
