import com.sun.source.doctree.SystemPropertyTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Advent of Code 2023, Day 3:
// https://adventofcode.com/2023/day/3

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("AdventOfCode_3_sisend.txt");
        osadeSumma(sisendFail);
    }

    // Abifunktsioon faili sisselugemiseks
    public static List<String> loeFail(FileReader sisend) throws IOException{
        List<String> failiRead = new ArrayList<String>();
        try {
            // Loe fail
            BufferedReader loe = new BufferedReader(sisend);
            String rida = null;
            while ((rida = loe.readLine()) != null) {
                failiRead.add(rida);
            }
            loe.close();
        } catch (IOException lugemisviga) {
            System.out.println("Ei suutnud faili lugeda.");
        }
        return failiRead;
    }

    // Abifunktsioon tuvastamaks kas char on digit või "."
    public static boolean kasOnDigitV6iPunkt(char t2ht) {
        // Sümbolite list
        List<Character> symboliteList = new ArrayList<>();
        symboliteList.add('0');
        symboliteList.add('1');
        symboliteList.add('2');
        symboliteList.add('3');
        symboliteList.add('4');
        symboliteList.add('5');
        symboliteList.add('6');
        symboliteList.add('7');
        symboliteList.add('8');
        symboliteList.add('9');
        symboliteList.add('.');

        // Kas t2ht on listis
        return symboliteList.contains(t2ht);
    }

    public static void osadeSumma(FileReader sisend) throws IOException {
        // Loo muutujad
        String rida;
        String eelmineRida = null;
        String j2rgmineRida = null;
        String integerStringina;
        Integer arvudeSumma = 0;
        Integer reaSumma = 0;
        Integer algus = 0;
        Integer l6pp = 0;
        Integer integerArvuna = 0;
        List<String> andmed = new ArrayList<String>();
        List<Integer> reaArvud = new ArrayList<Integer>();
        List<Integer> rea6igedArvud = new ArrayList<Integer>();
        List<Integer> algused = new ArrayList<Integer>();
        List<Integer> l6pud = new ArrayList<Integer>();
        Pattern pattern;
        Matcher matcher;

        try {
            // Defineeri andmed varem sisse loetud faili sisuna
            andmed = loeFail(sisend);
            pattern = Pattern.compile("\\d+");

            // Iga rea kohta
            // St iga elemendi kohta stringide listis
            // i = rea number ehk [0; andmed.size()-1]
            for (int i = 0; i < andmed.size(); i++) {
                // Nulli rea arvude summa
                reaSumma = 0;
                // Nulli rea arve ja indekseid hoidvad listid
                reaArvud.clear();
                algused.clear();
                l6pud.clear();
                // Tee rea nn õigete arvude list tühjaks
                rea6igedArvud.clear();

                // Praegu loetav rida
                rida = andmed.get(i);
                // Teen rea pikkuse koordinaatidena
                int reaPikkusKoordinaatides = rida.length() - 1;

                // Eelmine rida on olemas ainult siis,
                // kui i > 0, st me ei ole esimesel real.
                if (i > 0) {
                    eelmineRida = andmed.get(i - 1);
                }
                // Järgmine rida on olemas ainult siis,
                // kui i < (array.size()-1), st me ei ole viimasel real
                if (i < (andmed.size() - 1)) {
                    j2rgmineRida = andmed.get(i + 1);
                }

                // Leia reas number i asuvad arvud ja koordinaadid
                matcher = pattern.matcher(andmed.get(i));
                while (matcher.find()) {
                    algus = matcher.start();
                    // Nii on lõpukoordinaat arvu lõpp
                    l6pp = matcher.end()-1;
                    integerStringina = matcher.group();
                    integerArvuna = Integer.parseInt(integerStringina);
                    System.out.println("Leidsin arvu: " + integerArvuna + " asukohaga [" + algus + ", " + l6pp + "]");

                    // Lisa arvud ja nende koordinaadid listidesse
                    reaArvud.add(integerArvuna);
                    algused.add(algus);
                    l6pud.add(l6pp);
                }

                // Trüki leitud arvud kontrolliks välja
                System.out.println("Selles reas on arvud: " + reaArvud);

                // Kontrolli reast leitud arvude osas, kas nad sobivad
                // ülesande tingimustega.
                // j = Leitud arvu järjekord listis ehk [0; reaArvud.size() -1]
                for (int j = 0; j < reaArvud.size(); j++) {
                    // Abisuurused
                    // Arvu j algkoordinaat ehk asukoht reas i
                    int arvuAlgus = algused.get(j);
                    // System.out.println("Algkoordinaat: " + arvuAlgus);
                    // Arvu j lõppkoordinaat ehk viimase liikme asukoht reas i
                    int arvuL6pp = l6pud.get(j);
                    // System.out.println("Lõppkoordinaat: " + arvuL6pp);

                    ///////// Ridade kontroll //////////
                    // Kui tegemist on esimese reaga
                    // kontrolli sama ja järgmise rea sümboleid
                    if (eelmineRida == null) {
                        // Kui ei ole ei rea alguses ega lõpus
                        if (arvuAlgus != 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus -1)) || !kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea alguses
                        else if (arvuAlgus == 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))) {
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = arvuAlgus; k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea lõpus
                        else if (arvuAlgus != 0 && arvuL6pp == reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus -1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= arvuL6pp; k++) {
                                    if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                    }
                    // Kui tegemist on viimase reaga
                    // kontrolli eelmise ja selle rea sümboleid
                    else if (eelmineRida != null && j2rgmineRida == null) {
                        // Kui ei ole ei rea alguses ega lõpus
                        if (arvuAlgus != 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus - 1)) || !kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea alguses
                        else if (arvuAlgus == 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = arvuAlgus; k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea lõpus
                        else if (arvuAlgus != 0 && arvuL6pp == reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus -1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= arvuL6pp; k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                    }
                    // Kui tegemist ei ole esimese ega viimase reaga,
                    // kontrolli selle eelmise ja järgmise rea sümboleid
                    else if (eelmineRida != null && j2rgmineRida != null){
                        // Kui ei ole ei rea alguses ega lõpus
                        if (arvuAlgus != 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus - 1)) || !kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))) {
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    } else if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea alguses
                        else if (arvuAlgus == 0 && arvuL6pp < reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuL6pp + 1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = arvuAlgus; k <= (arvuL6pp + 1); k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                    else if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                        // Kui on rea lõpus
                        else if (arvuAlgus != 0 && arvuL6pp == reaPikkusKoordinaatides) {
                            if (!kasOnDigitV6iPunkt(rida.charAt(arvuAlgus - 1))){
                                rea6igedArvud.add(reaArvud.get(j));
                            } else {
                                for (int k = (arvuAlgus - 1); k <= arvuL6pp; k++) {
                                    if (!kasOnDigitV6iPunkt(eelmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                    else if (!kasOnDigitV6iPunkt(j2rgmineRida.charAt(k))) {
                                        rea6igedArvud.add(reaArvud.get(j));
                                    }
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("Nõuetele vastavaid arve ei ole.");
                    }
                }
                // Trüki õiged arvud
                System.out.println("Summasse lähevad arvud: " + rea6igedArvud);

                // Liida selle rea arvude array sisu kokku;
                for (int m = 0; m < rea6igedArvud.size(); m++) {
                    reaSumma = reaSumma + rea6igedArvud.get(m);
                }
                System.out.println("Rea summa on: " + reaSumma);

                // Uuenda arvude summa väärtust
                arvudeSumma = arvudeSumma + reaSumma;
                System.out.println("Seni leitud arvude summa on " + arvudeSumma);

            }
        } catch (Exception viga) {
            System.out.println(viga);
        }
    }
}