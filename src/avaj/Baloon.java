package avaj;

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

    @Override
    public void updateConditions() {
        HashMap<String, String> message = new HashMap<>();
        message.put("FOG", "baloon-fog");
        message.put("RAIN", "Damn you rain! You messed up my baloon.");
        message.put("SNOW", "It's snowing. We're gonna crash.");
        message.put("SUN", "Let's enjoy the good weather and take some pics.");

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

        if (this.coordinates.getHeight() <= 0) {
            System.out.println(this.type + "#" + this.name + "(" + this.id + ") landing.");
            System.out.println("Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.type + "#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    };
}
