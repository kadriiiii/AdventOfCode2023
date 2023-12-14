import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// Advent of Code 2023, Day 3:
// https://adventofcode.com/2023/day/3

public class Main {
    public static void main(String[] args)  throws IOException {
                FileReader sisendFail = new FileReader("AdventOfCode_3_sisend.txt");
                osadeSumma(sisendFail);
            }

    public static Integer  loeRidadeArv(FileReader sisend) throws IOException {
        Integer ridadeArv = 0;
        try (BufferedReader reader = new BufferedReader(sisend)) {
            while (reader.readLine() != null) {
                ridadeArv++;
            }
            return ridadeArv;
        }
    }

    public static void osadeSumma(FileReader sisend) throws IOException {
        // Loo muutujad
        BufferedReader loe;
        String esimeneRida;
        String rida;
        String integerStringina;
        String[] reaNumbrid;
        String[] v2rvid;
        String[] v2rvidListi;
        Integer iMax = 0;
        Integer jMax = 0;
        Integer arvudeSumma = 0;
        Integer reaSumma = 0;
        Integer algus = 0;
        Integer l6pp = 0;
        Integer integerArvuna = 0;
        List<Integer> reaArvud = new ArrayList<Integer>();
        List<Integer> algused = new ArrayList<Integer>();
        List<Integer> l6pud = new ArrayList<Integer>();
        Pattern arvudeEraldaja;
        Matcher v6rdleja;


        // Leia maatriksi suurus
        try {
            //////// Leia maatriksi suurus
            // Hiljem selgub, kas on üldse vaja :D

            // Loe fail
            loe = new BufferedReader(sisend);
            rida = loe.readLine();

            // Leia esimese rea elementide arv iMax
            //esimeneRida = loe.readLine();
            //iMax = esimeneRida.length() - 1;

            // Leia ridade arv jMax
            // BufferReader olevat old school :)
            //jMax = loeRidadeArv(sisend);

            // Trüki tulemused välja
            //System.out.println("Rea pikkus: " + iMax);
            //System.out.println("Ridade arv: " + jMax);

            ///////// Leia igast reast integerid ja nende koordinaadid
            arvudeEraldaja = Pattern.compile("\\d+");

            for (arvudeSumma = 0; rida != null; rida = loe.readLine()) {
                reaSumma = 0;
                reaArvud.clear();
                v6rdleja = arvudeEraldaja.matcher(rida);
                while (v6rdleja.find()) {
                    algus = v6rdleja.start();
                    l6pp = v6rdleja.end();
                    integerStringina = v6rdleja.group();
                    integerArvuna = Integer.parseInt(integerStringina);
                    System.out.println("Leidsin arvu: " + integerArvuna + " asukohaga [" + algus + ", " + l6pp + "]");

                    // Lisa arvud ja nende koordinaadid listidesse
                    reaArvud.add(integerArvuna);
                    algused.add(algus);
                    l6pud.add(l6pp);
                }

                System.out.println("Siin reas on arvud: " + reaArvud);

                // Liida selle rea arvude array sisu kokku
                int i = 0;
                for (i = 0; i < reaArvud.size(); i++) {
                    reaSumma = reaSumma + reaArvud.get(i);
                };

                arvudeSumma = arvudeSumma + reaSumma;
                System.out.println("Seni leitud arvude summa on " + arvudeSumma);
                //System.out.println(rida);
            }
        } catch (Exception viga) {

            System.out.println("Viga");
        }

    }
}