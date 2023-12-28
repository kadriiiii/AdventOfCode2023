import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Ülesande andmed
        FileReader sisendFail = new FileReader("..\\sisend\\AdventOfCode2023_6_sisend.txt");
        // Näiteandmed
        //FileReader sisendFail = new FileReader("AdventOfCode2023_6_n2iteAndmed.txt");
        // Ülesanne 1 ja 2
        waysToBeatTheRecord(sisendFail);
    }

    ///////////////////////////////////////
    // Abimeetodid
    /////////////////

    // Abimeetod faili lugemiseks
    // Loeb kõik read (ka tühjad) kuni ridu on.
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

    // Sobilike distantside arvutamine
    public static ArrayList<BigInteger> sobivadDistantsid(ArrayList<BigInteger> ajad, ArrayList<BigInteger> vahemaad) {
        // Lõppandmete hoiukoht
        ArrayList<BigInteger> sobivateVahemaadeArv = new ArrayList<BigInteger>();
        // Üle kõigi distantside
        for (int j = 0; j < vahemaad.size(); j++) {
            //System.out.println("Töötlen võistlust, kus max distants on: " + vahemaad.get(j));
            // Võistluse kestus, millisekundites
            BigInteger t = ajad.get(j);
            //System.out.println("Aeg " + t);
            // Laadimisaeg, millisekundites
            BigInteger tL = BigInteger.valueOf(1);
            // Kiirus, millimeetrit millisekundis.
            // Numbriliselt sama, mis tL, sest iga ms laadimisega
            // suureneb kiirus 1 mm/ms.
            BigInteger v = BigInteger.valueOf(0);
            // Vahemaa, mille jõuaksin läbida, millimeetrites
            BigInteger d = BigInteger.valueOf(0);
            // Sobilikud vahemaad
            BigInteger dK = BigInteger.valueOf(0);
            // Suurendan laadimisaega ja kiirust.
            // 0 ja t võib välistada, sest siis on vahemaa 0.
            for ( tL.compareTo(BigInteger.valueOf(1)); tL.compareTo(t) < 0; tL = tL.add(BigInteger.valueOf(1))) {
                // Laadimisaeg
                //System.out.println("Laadimisaeg: " + tL);
                // Kiirus
                v = tL;
                //System.out.println("Kiirus: " + v);
                // Distants
                d = v.multiply(t.subtract(tL));
                //System.out.println("Distants: " + d);
                // Kui on suurem kui senine rekord, siis salvesta
                if ( d.compareTo(vahemaad.get(j)) > 0) {
                    dK = dK.add(BigInteger.valueOf(1));
                }
            }
            // Salvesta selle etapi sobilike distantside arv
            sobivateVahemaadeArv.add(dK);
        }
        return sobivateVahemaadeArv;
    }

    ///////////////////////////////////////////
    // Ülesanne 1 ja 2
    ///////////////////////////////////////////
    public static void waysToBeatTheRecord(FileReader sisend) {
        try {
            //////////////////////////////////////////////
            // Muudan lähteandmed söödavaks.
            /////////////////////////////////////////////

            // Loe sisendfailist andmed.
            List<String> readFailist = loeFail(sisend);

            // Prindi kontrolliks välja (ainult 2 rida, pole hullu).
            // for (String s : readFailist) {
            //     System.out.println(s);
            // }

            // Jäta mõlemast reast järele ainult arvud.
            // Esimene ride on Time ehk võistluse aeg.
            // Teine rida on Distance ehk vahemaa, mille pean ületama.
            String timeFailist = readFailist.get(0).substring(readFailist.get(0).indexOf(":") + 1);
            String distanceFailist = readFailist.get(1).substring(readFailist.get(1).indexOf(":") + 1);
            System.out.println(timeFailist);
            System.out.println(distanceFailist);

            //////// Ülesande 1 jaoks
            // Eemalda liigsed tühikud ja teisenda Integeride listiks.
            String timeFailist1 = timeFailist.trim();
            String distanceFailist1 = distanceFailist.trim();
            String[] timesAsStrings = timeFailist1.split(" +");
            String[] distsAsStrings = distanceFailist1.split(" +");
            ArrayList<BigInteger> times1 = new ArrayList<BigInteger>();
            ArrayList<BigInteger> dists1 = new ArrayList<BigInteger>();
            // Kuna failis oli palju tühikuid, siis uuesti trim.
            // Siis Integeriks ja listi "times".
            for (int i = 0; i < timesAsStrings.length; i++) {
                timesAsStrings[i] = timesAsStrings[i].trim();
                BigInteger times_temp = new BigInteger(String.valueOf(timesAsStrings[i]));
                times1.add(times_temp);
                distsAsStrings[i] = distsAsStrings[i].trim();
                BigInteger dists_temp = new BigInteger(String.valueOf(distsAsStrings[i]));
                dists1.add(dists_temp);
            }
            // Prindi kontrolliks välja
            // System.out.println(times);
            // System.out.println(dists);

            //////// Ülesande 2 jaoks
            // Eemalda kõik tühikud ja teisenda numbrite jada Integeriks.
            String timeFailist2 = timeFailist.replaceAll(" ", "");
            String distanceFailist2 = distanceFailist.replaceAll(" ", "");
            // System.out.println("String timeFailist2 " + timeFailist2 + " ja String distanceFailist2 " + distanceFailist2);
            BigInteger times2 = new BigInteger(String.valueOf(timeFailist2));
            BigInteger dists2 = new BigInteger(String.valueOf(distanceFailist2));
            // Teisendan üheelemendilisteks listideks, kuna distantside
            // arvutamise meetod vajab sisendina ArrayList<Integer>()
            ArrayList<BigInteger> times2List = new ArrayList<BigInteger>();
            ArrayList<BigInteger> dists2List = new ArrayList<BigInteger>();
            times2List.add(times2);
            dists2List.add(dists2);
            // Prindi kontrolliks välja
            // System.out.println(times2List);
            // System.out.println(dists2List);
            /////////////////////////////////
            // Sobilike laadimisaegade ja kiiruste kombinatsioonide leidmine.
            /////////////////////////////////


            //// Ülesanne 1
            // Mitu erinevat võistlust.
            // Leia iga võistluse sobilike distantside arv ja siis nende arvude korrutis.
            // Distantsid, mis selle aja puhul oleksid suuremad kui dMax
            ArrayList<BigInteger> sobilikudDists1 = new ArrayList<BigInteger>();
            // Üle kõigi distantside
            sobilikudDists1 = sobivadDistantsid(times1, dists1);
            System.out.println("Sobilike distantside arvud: " + sobilikudDists1);
            // Sobilike distantside arvu korrutis
            BigInteger korrutis1 = BigInteger.valueOf(1);
            for (int n = 0; n < sobilikudDists1.size(); n++) {
                korrutis1 = korrutis1.multiply(sobilikudDists1.get(n));
            }

            //// Ülesanne 2
            // Üks võistlus.
            // Leia sobilike distantside arv.
            // Distantsid, mis selle aja puhul oleksid suuremad kui dMax
            ArrayList<BigInteger> sobilikudDists2 = new ArrayList<BigInteger>();
            // Üle kõigi distantside
            sobilikudDists2 = sobivadDistantsid(times2List, dists2List);
            System.out.println("Sobilike distantside arvud: " + sobilikudDists2);
            // Sobilike distantside arvu korrutis
            BigInteger korrutis2 = BigInteger.valueOf(1);
            for (int n = 0; n < sobilikudDists2.size(); n++) {
                korrutis2 = korrutis2.multiply(sobilikudDists2.get(n));
            }

            // 633080
            System.out.println("Mitme võistluse sobilike distantside arvude korrutis: " + korrutis1);
            System.out.println("Ühe võistluse sobilike distantside arvude korrutis: " + korrutis2);

        } catch (Exception viga) {
            System.out.println(viga);
        }
    }

}
