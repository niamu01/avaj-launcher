package avaj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static int count;
    private static WeatherTower wt;// = new weatherTower();

    public static class SimulationException extends Exception {
        public SimulationException() { super(); }
        public SimulationException(String str) { super (str); }
    }

    private static void init(File path) {
//        BufferedReader reader = new BufferedReader(
//                new FileReader("d:\\file.txt")
//        );

//        String str;
//        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
//        }
        List<String> line= new ArrayList<>();
        String str;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            count = Integer.parseInt(reader.readLine()); //NumberFormatException
            while ((str = reader.readLine()) != null) {
                line.add(str);
//            makeAircraft();
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: argv");
        }

        try {
            init(new File(args[1]));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (count-- != 0) {
            wt.changeWeather();
        }
    }
}
