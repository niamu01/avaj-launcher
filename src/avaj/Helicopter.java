package avaj;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private final String type = "Helicopter";
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    };

//fog
//rain
//snow  My rotor is going to freeze!
//sun   This is hot.

//Helicopter:
//  - fog: +1 / 0 / 0
//  - rain: +5 / 0 / 0
//  - snow: 0 / 0 / -12
//  - sun: +10 / 0 / +2
    @Override
    public void updateConditions() {
        HashMap<String, String> message = new HashMap<>();
        message.put("FOG", "helicopter-fog");
        message.put("RAIN", "helicopter-rain");
        message.put("SNOW", "My rotor is going to freeze!");
        message.put("SUN", "This is hot.");

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
//        System.out.println("position: " + this.coordinates.getLongitude() + " " + this.coordinates.getLatitude() + " " + this.coordinates.getHeight());

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this.type + "#" + this.name + "(" + this.id + ") landing.");
            System.out.println("Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
            weatherTower.unregister(this);
        }
    };

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    };
}
