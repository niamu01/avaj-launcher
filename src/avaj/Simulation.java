package avaj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Simulation {
    private static int count;
    private static WeatherTower wt;// = new weatherTower();
// nongdamgom
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
        ).registerTower(wt);
    }

    private static void init(String path) {
        String str;
        try{
            File simluationFile = new File(path);
            if (!simluationFile.canRead())
                throw new FileNotFoundException();
            BufferedReader reader = new BufferedReader(new FileReader(simluationFile));
            count = Integer.parseInt(reader.readLine()); //NumberFormatException
            while ((str = reader.readLine()) != null) {
//                System.out.println(str);
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
//            init(args[0]);
            init("/Users/juyeeun/Desktop/AvajLauncher/src/avaj/scenario2.txt");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
// yeju ineedyou
        while (count-- != 0) {
            wt.changeWeather();
        }
    }
}
