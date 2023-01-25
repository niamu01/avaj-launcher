package avaj;

import java.security.MessageDigest;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private final String type = "Baloon";
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

//fog
//rain  Baloon#B1(1): Damn you rain! You messed up my baloon.
//snow  Baloon#B1(1): It's snowing. We're gonna crash.
//sun   Baloon#B1(1): Let's enjoy the good weather and take some pics.

//Baloon:
//  - fog : 0 / 0 / -3
//  - rain: 0 / 0 / -5
//  - snow: 0 / 0 / -15
//  - sun : +2 / 0 / +4
public static final String RESET = "\u001B[0m";

public static final String FOG = "\u001B[38;5;177m";
public static final String SNOW = "\u001B[38;5;195m";
public static final String RAIN = "\u001B[38;5;27m";
public static final String SUN = "\u001B[38;5;220m";

public static final String WHITE_BACKGROUND = "\u001B[47m";
public static final String BLACK = "\u001B[30m";

// enum Color {
//     FOG(177),
//     SNOW(195),
//     RAIN(27),
//     SUN(220);
// }

// public static void printColor(String str, enum color) {
//     System.out.println();
//     message.put(color + str + RESET);
// }

@Override
public void updateConditions() {
        HashMap<String, String> message = new HashMap<>();
        message.put("FOG", FOG + "baloon-fog" + RESET);
        message.put("RAIN", RAIN + "Damn you rain! You messed up my baloon." + RESET);
        message.put("SNOW", SNOW + "It's snowing. We're gonna crash." + RESET);
        message.put("SUN", SUN + "Let's enjoy the good weather and take some pics." + RESET);

        String weather = this.weatherTower.getWeather(this.coordinates);
        if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
        } else if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
        } else if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
        } else if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
        }

        String msg = message.get(weather);
        System.out.println(this.type + "#" + this.name + "(" + this.id + "): " + msg);
//        System.out.println("position: " + this.coordinates.getLongitude() + " " + this.coordinates.getLatitude() + " " + this.coordinates.getHeight());

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this.type + "#" + this.name + "(" + this.id + ") landing.");
            System.out.println(WHITE_BACKGROUND + BLACK + "Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " unregistered from weather tower." + RESET);
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println(WHITE_BACKGROUND + BLACK + "Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " registered to weather tower." + RESET);
    };
}
