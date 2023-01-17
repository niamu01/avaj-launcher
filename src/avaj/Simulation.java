package avaj;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

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

    private static void init(String path) {
//        BufferedReader reader = new BufferedReader(
//                new FileReader("d:\\file.txt")
//        );

//        String str;
//        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
//        }
        String str;
        try{
//            System.out.println("0");
            File c = new File("/Users/juyeeun/Desktop/AvajLauncher/src/avaj/scenario.txt");
            System.out.println("1");
            if (!c.canRead())
                System.out.println("can't read");
            FileReader fr = new FileReader(c); //error
            System.out.println("1.5");
            FileReader fr2 = new FileReader(c); //error
            System.out.println("2");
            BufferedReader reader = new BufferedReader(fr);
            System.out.println("3");
//            BufferedReader reader = new BufferedReader(new FileReader(path));
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
            init(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("error: init function");
            System.out.println(e.getMessage());
            return;
        }

        while (count-- != 0) {
            wt.changeWeather();
        }
    }
}
