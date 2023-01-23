package avaj;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private final String type = "JetPlane";
    JetPlane (String name, Coordinates coordinates) {
        super(name, coordinates);
    };

//fog
//rain  It's raining. Better watch out for lightings.
//snow  OMG! Winter is coming!
//sun

//Helicopter:
//  - fog: +1 / 0 / 0
//  - rain: +5 / 0 / 0
//  - snow: 0 / 0 / -12
//  - sun: +10 / 0 / +2

    public void updateConditions() {
        HashMap<String, String> message = new HashMap<>();
        message.put("FOG", "jetplane-fog");
        message.put("RAIN", "It's raining. Better watch out for lightings.");
        message.put("SNOW", "OMG! Winter is coming!");
        message.put("SUN", "jetplane-sun");

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
