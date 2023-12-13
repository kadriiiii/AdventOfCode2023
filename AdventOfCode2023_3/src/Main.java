import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("AdventOfCode2023_3_sisend");

        sumOfThings(sisendFail);
    }

    public static void sumOfThings(FileReader sisend) throws IOException {
        // Loo muutujad
        BufferedReader loe;
        String rida;
        Integer sumOfParts;

        // Loe fail
        try {
            loe = new BufferedReader(sisend);
            rida = loe.readLine();

            for (sumOfParts = 0; rida != null; rida = loe.readLine()) {
                String numbrid = rida.replaceAll("[^0-9]", "");
                System.out.println("Leidsin numbrid: " + numbrid);
                // Numbrite koordinaadid
                // String koordinaadid ksikKalibratsiooniParameeterTekstina = new StringBuilder().append(numbrid.charAt(0))
            }


        } catch (IOException viga) {
            System.out.println("Viga");
        }
    }

}