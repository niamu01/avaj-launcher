package avaj;

public class AircraftFactory {
    //longitude 경도
    //latitude 위도
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
//        System.out.println("start newAircraft: " + type + name + longitude + latitude + height);
        if (longitude < 0 || latitude < 0 || height < 0) {
            System.out.printf("%d %d %d\n", longitude, latitude, height);
            throw new RuntimeException("Error newAircraft: value");
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        if (type.equals("Helicopter")) {
            System.out.println(type + " " + name + " has been created.");
            return new Helicopter(name, coordinates);
        }
        else if (type.equals("JetPlane")) {
            System.out.println(type + " " + name + " has been created.");
            return new JetPlane(name, coordinates);
        }
        else if (type.equals("Baloon")) {
            System.out.println(type + " " + name + " has been created.");
            return new Baloon(name, coordinates);
        }
        return null;
    };
}
