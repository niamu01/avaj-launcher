package avaj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Simulation {
    private static int count;
    private static WeatherTower wt;// = new weatherTower();

    public static class SimulationException extends Exception {
        public SimulationException() { super(); }
        public SimulationException(String str) { super (str); }
    }

    private static void makeAircraft(String str) {
        wt = new WeatherTower();
        String[] parse = str.split(" ");
        AircraftFactory.newAircraft(
            parse[0], 
            parse[1], 
            Integer.parseInt(parse[2]), 
            Integer.parseInt(parse[3]), 
            Integer.parseInt(parse[4])
        );
    }

    private static void init(File path) {
//        BufferedReader reader = new BufferedReader(
//                new FileReader("d:\\file.txt")
//        );

//        String str;
//        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
//        }
        String str;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            count = Integer.parseInt(reader.readLine()); //NumberFormatException
            while ((str = reader.readLine()) != null) {
                makeAircraft(str);
            }
            reader.close();
        } catch (Exception e) {
            throw new NumberFormatException();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: argv");
        }

        try {
            init(new File(args[0]));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (count-- != 0) {
            wt.changeWeather();
        }
    }
}
