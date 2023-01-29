package avaj;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private final String type = "Helicopter";
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    };

    public static final String RESET = "\u001B[0m";
    public static final String FOG = "\u001B[38;5;177m";
    public static final String SNOW = "\u001B[38;5;195m";
    public static final String RAIN = "\u001B[38;5;27m";
    public static final String SUN = "\u001B[38;5;220m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String BLACK = "\u001B[30m";

    @Override
    public void updateConditions() {
        HashMap<String, String> message = new HashMap<>();
        message.put("FOG", FOG + "helicopter-fog" + RESET);
        message.put("RAIN", RAIN + "helicopter-rain" + RESET);
        message.put("SNOW", SNOW + "My rotor is going to freeze!" + RESET);
        message.put("SUN", SUN + "This is hot." + RESET);

        String weather = this.weatherTower.getWeather(this.coordinates);
        if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
        } else if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
        } else if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
        } else if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
        }

        String msg = message.get(weather);
        System.out.println(this.type + "#" + this.name + "(" + this.id + "): " + msg);

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this.type + "#" + this.name + "(" + this.id + ") landing.");
            System.out.println(WHITE_BACKGROUND + BLACK + "Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " unregistered from weather tower." + RESET);
            weatherTower.unregister(this);
        }
    };

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println(WHITE_BACKGROUND + BLACK + "Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " registered to weather tower." + RESET);
    };
}
