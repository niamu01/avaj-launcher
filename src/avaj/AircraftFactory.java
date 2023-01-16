package avaj;

public class AircraftFactory {
    //longitude 경도
    //latitude 위도
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new RuntimeException("Error newAircraft: value");
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        if (type.equals("helicopter")) {
            System.out.println(type + " " + name + " has been created.");
            return new Helicopter(name, coordinates);
        }
        else if (type.equals("jetplane")) {
            System.out.println(type + " " + name + "  has been created.");
            return new JetPlane(name, coordinates);
        }
        else if (type.equals("baloon") || type.equals("baloon") ) {
            System.out.println(type + " " + name + "  has been created.");
            return new Baloon(name, coordinates);
        }
        return null;
    };
}
