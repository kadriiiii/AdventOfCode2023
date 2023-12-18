import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

// Advent of Code 2023, Day 4:
// https://adventofcode.com/2023/day/4

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("AdventOfCode2023_4_sisend.txt");
        // Ülesanne 1
        // v6idud(sisendFail);
        // Ülesanne 2
        kaardid(sisendFail);
    }

    /////////////////////////////////////
    // Abifunktsioonid
    /////////////////////////////////////

    // Abifunktsioon faili sisse lugemiseks
    public static List<String> loeFail(FileReader sisend) throws IOException {
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

    // Abifunktsioon sub-stringi leidmiseks, mis on kahe teadaoleva stringi vahel.
    // Pärineb päeva 2 ülesandest.
    public static String kaheVahel(String value, String a, String b) {
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

//    //////////////////////////////////////
//    // Ülesanne 1
//    //////////////////////////////////////
//
//    public static void v6idud(FileReader sisend) throws IOException {
//        try {
//            // Loe failist kaartide andmed
//            List<String> andmed = loeFail(sisend);
//            System.out.println("Sisendandmed: ");
//            for (String s : andmed) {
//                System.out.println(s);
//            }
//
//            // Loe võidunumbrid
//            // Pärast "Card x: " ja enne "|"
//            // Loo tühi võisdnumbrite list
//            String[] v6iduNumbrid;
//            List<Integer> v6iduNumbridInt = new ArrayList<Integer>();
//            // Loo tühi päkapiku numbrite list
//            String[] p2kaNumbrid;
//            List<Integer> p2kaNumbridInt = new ArrayList<Integer>();
//            // Loo mängupunktide jaoks tühi list
//            List<Integer> m2nguPunktid = new ArrayList<Integer>();
//            // Mängude summa
//            int m2ngudeSumma = 0;
//            int eelmiseKaardiNumber = 0;
//
//            // Iga kaardi kohta
//            for (String s : andmed) {
//                String kaardiNumber = kaheVahel(s, "Card ", ":");
//                kaardiNumber = kaardiNumber.trim();
//                int kaardiNumberInt = Integer.valueOf(kaardiNumber);
//                // Võidunumbrid
//                String kaardiInfo = kaheVahel(s, ": ", " |");
//                System.out.println("Kaardi nr " + kaardiNumber + " võidunumbrid on " + kaardiInfo);
//
//                // Mängunumbrid
//                // Lisan rea lõppu semikooloni,
//                // et funktsioonil kaheVahel oleks lõppsümbol
//                String s1 = s + ";";
//                String m2nguInfo = kaheVahel(s1, "|", ";");
//                System.out.println("Kaardi nr " + kaardiNumber + " mängunumbrid on " + m2nguInfo);
//
//                // Eralda võidunumbrid tühikute abil listiks.
//                // Eemalda eelmiselt realt leitud numbrid.
//                v6iduNumbridInt.clear();
//                // Võta algandmete algusest ja lõpust tühikud ära.
//                kaardiInfo = kaardiInfo.trim();
//                v6iduNumbrid = kaardiInfo.split(" {1,3}");
//                // Konverteeri numbriteks
//                for (String t : v6iduNumbrid) {
//                    int ajutineArv = Integer.valueOf(t);
//                    v6iduNumbridInt.add(Integer.valueOf(ajutineArv));
//                }
//                // System.out.println("Võidunumbrid numbritena: " + v6iduNumbridInt);
//
//                // Eralda m2ngunumbrid tühikute abil listiks.
//                // Eemalda eelmiselt realt leitud numbrid.
//                p2kaNumbridInt.clear();
//                // Võta algandmete algusest ja lõpust tühikud ära.
//                m2nguInfo = m2nguInfo.trim();
//                p2kaNumbrid = m2nguInfo.split(" {1,3}");
//                // Konverteeri numbriteks
//                for (String r : p2kaNumbrid) {
//                    int ajutineArv = Integer.valueOf(r);
//                    p2kaNumbridInt.add(Integer.valueOf(ajutineArv));
//                }
//                // System.out.println("Mängunumbrid numbritena: " + p2kaNumbridInt);
//
//                // Võrdle võidunumbreid ja mängunumbreid
//                // ning leia mitu on ühesuguseid elemente.
//                v6iduNumbridInt.retainAll(p2kaNumbridInt);
//                System.out.println("Kattuvad numbrid: " + v6iduNumbridInt);
//                // Ühesuguste elementide arv
//                int yhesugused = v6iduNumbridInt.size();
//                System.out.println("Selle mängu kattuvaid numbreid on: " + yhesugused);
//                double punktid = Math.pow(2, (yhesugused - 1));
//                int punktidInt = (int) punktid;
//                System.out.println("Selle mängu punktid on: " + punktidInt);
//                // Lisa konkreetse mängu punktid listi
//                m2nguPunktid.add(punktidInt);
//            }
//
//            // Leia kõikide mängude summa
//            for (Integer integer : m2nguPunktid) {
//                m2ngudeSumma = m2ngudeSumma + integer;
//            }
//
//            // Vastus: 24175
//            System.out.println("Mängude summa on: " + m2ngudeSumma);
//        } catch (Exception viga) {
//            System.out.println(viga);
//        }
//    }

    //////////////////////////////////////
    // Ülesanne 2
    //////////////////////////////////////

    // Kaardista andmed
    // Map<kaardi number, yhesuguste arv>
    public static void kaardid(FileReader sisend) throws IOException {
        try {
            // Loe failist kaartide andmed
            List<String> andmed = loeFail(sisend);
            // Konverteerin Arrayks, et vältida ConcurrentModificationExceptionit
            String[] andmedArr = andmed.toArray(new String[0]);
            //System.out.println("Sisendandmed: ");
            //for (String s : andmed) {
            //    System.out.println(s);
            //}

            // Loe võidunumbrid
            // Pärast "Card x: " ja enne "|"
            // Loo tühi võisdnumbrite list
            String[] v6iduNumbrid;
            List<Integer> v6iduNumbridInt = new ArrayList<Integer>();
            // Loo tühi päkapiku numbrite list
            String[] p2kaNumbrid;
            List<Integer> p2kaNumbridInt = new ArrayList<Integer>();
            // Loo mängupunktide jaoks tühi list
            List<Integer> m2nguPunktid = new ArrayList<Integer>();
            // Loo tühi Map kaardinumbrite ja ühesuguste jaoks
            // Map<Kaardi number, ühesuguste arv>
            Map<Integer, Integer> kaardiNumbridJaYhesugused = new HashMap<Integer, Integer>();
            // Map<Kaardi number, sisu ehk rida>
            Map<Integer, String> kaardiNumbridJaSisu = new HashMap<Integer,String>();

            int n = 0;
            // Iga kaardi kohta
            while (n < Arrays.asList(andmedArr).size()) {
                String s = Arrays.asList(andmedArr).get(n);
                String kaardiNumber = kaheVahel(s, "Card ", ":");
                kaardiNumber = kaardiNumber.trim();
                int kaardiNumberInt = Integer.valueOf(kaardiNumber);
                // Võidunumbrid
                String kaardiInfo = kaheVahel(s, ": ", " |");
                System.out.println("Kaardi nr " + kaardiNumber + " võidunumbrid on " + kaardiInfo);

                // Mängunumbrid
                // Lisan rea lõppu semikooloni,
                // et funktsioonil kaheVahel oleks lõppsümbol
                String s1 = s + ";";
                String m2nguInfo = kaheVahel(s1, "|", ";");
                System.out.println("Kaardi nr " + kaardiNumber + " mängunumbrid on " + m2nguInfo);

                // Eralda võidunumbrid tühikute abil listiks.
                // Eemalda eelmiselt realt leitud numbrid.
                v6iduNumbridInt.clear();
                // Võta algandmete algusest ja lõpust tühikud ära.
                kaardiInfo = kaardiInfo.trim();
                v6iduNumbrid = kaardiInfo.split(" {1,3}");
                // Konverteeri numbriteks
                for (String t : v6iduNumbrid) {
                    int ajutineArv = Integer.valueOf(t);
                    v6iduNumbridInt.add(Integer.valueOf(ajutineArv));
                }
                // System.out.println("Võidunumbrid numbritena: " + v6iduNumbridInt);

                // Eralda m2ngunumbrid tühikute abil listiks.
                // Eemalda eelmiselt realt leitud numbrid.
                p2kaNumbridInt.clear();
                // Võta algandmete algusest ja lõpust tühikud ära.
                m2nguInfo = m2nguInfo.trim();
                p2kaNumbrid = m2nguInfo.split(" {1,3}");
                // Konverteeri numbriteks
                for (String r : p2kaNumbrid) {
                    int ajutineArv = Integer.valueOf(r);
                    p2kaNumbridInt.add(Integer.valueOf(ajutineArv));
                }
                // System.out.println("Mängunumbrid numbritena: " + p2kaNumbridInt);

                // Võrdle võidunumbreid ja mängunumbreid
                // ning leia mitu on ühesuguseid elemente.
                v6iduNumbridInt.retainAll(p2kaNumbridInt);
                System.out.println("Kattuvad numbrid: " + v6iduNumbridInt);
                // Ühesuguste elementide arv
                int yhesugused = v6iduNumbridInt.size();
                System.out.println("Selle mängu kattuvaid numbreid on: " + yhesugused);
                double punktid = Math.pow(2, (yhesugused - 1));
                int punktidInt = (int) punktid;
                System.out.println("Selle mängu punktid on: " + punktidInt);
                // Lisa konkreetse mängu punktid listi
                m2nguPunktid.add(punktidInt);

                // Lisa selle kaardi andmed HashMapi
                kaardiNumbridJaYhesugused.put(kaardiNumberInt, yhesugused);
                kaardiNumbridJaSisu.put(kaardiNumberInt, s);

                // Suurenda abinumbrit
                n++;
            }

            // Loo tühjad listid
            ArrayList<String> kaardidL6puks = new ArrayList<String>();
            // Lisan esimese kaardi
            kaardidL6puks.add(kaardiNumbridJaSisu.get(1));
            // Keys - kaartide numbrid
            Set<Integer> kaardiNumbridMapist = kaardiNumbridJaYhesugused.keySet();
            ArrayList<Integer> kaardiNumbrid1 = new ArrayList<Integer>(kaardiNumbridMapist);
            System.out.println("Kaartide numbrid HashMapist: " + kaardiNumbridMapist);
            // Values - ühesuguste arvude arv igal kaardil
            ArrayList<Integer> yhesugusteArvHashMapist = new ArrayList<Integer>(kaardiNumbridJaYhesugused.values());
            System.out.println("Ühesuguste arvude arv HashMapist: " + yhesugusteArvHashMapist);
            int j = 0;
            do {
                int kordaja = yhesugusteArvHashMapist.get(j);
                System.out.println("Kordaja: " + kordaja);
                if (kordaja != 0) {
                    for (int p = 1; (p <= kordaja && j+p < yhesugusteArvHashMapist.size()); p++) {
                        // Leia kui mitu seda kaardinumbrit kaardidL6puks reas juba on
                        // Nii mitu korda lisa neid ridu
                        kaardidL6puks.add(kaardiNumbridJaSisu.get(j + p));
                        System.out.println("Kaardid lõpuks");
                    }
                }
                j++;
            } while (j < kaardidL6puks.size());

            System.out.println("Lõpuks on " + kaardidL6puks.size() + " kaarti.");



        } catch (Exception viga) {
            System.out.println(viga);
        }
    }
}