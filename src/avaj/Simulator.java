package avaj;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Simulator {
    private static int count;
    private static WeatherTower wt;
    public static class SimulationException extends RuntimeException {
        public SimulationException() {
            super();
            System.out.println("simulation error");
        }
        public SimulationException(String str) { super (str); }
        public SimulationException(Exception e) { super (e); }
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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: argv");
        }

        try {
            String str;
            File simluationFile = new File(args[0]);
            //"/Users/juyeeun/Desktop/AvajLauncher/src/avaj/scenario.txt"
            if (!simluationFile.canRead())
                throw new SimulationException("FileNotFoundException");
            BufferedReader reader = new BufferedReader(new FileReader(simluationFile));
            count = Integer.parseInt(reader.readLine());
            while ((str = reader.readLine()) != null) {
                makeAircraft(str);
            }
            reader.close();
        } catch (IOException | SimulationException e) {
            throw new SimulationException(e);
        }
        while (count-- != 0) {
            wt.changeWeather();
        }
    }
}
