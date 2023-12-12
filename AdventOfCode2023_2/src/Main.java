import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;

// Advent of Code 2023, Day 2:
// https://adventofcode.com/2023/day/2
//
// IDE märkused:
// Day 1 oli kirjutatud Javas kasutades Ecplipse'i.
// Day 2 on kirjutatud Javas kasutades IntelliJ'd, mis on umbes mustmiljon korda mugavam.

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("AdventOfCode2023_2_sisend.txt");
        Integer red = 12;
        Integer green = 13;
        Integer blue = 14;

       //  m2ngudeSumma(sisendFail, red, green, blue);
        v2himateKorrutisteSumma(sisendFail);
    }

    ////////
    // Abifunktsioonid
    ////////
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

    // Tühjenda listid
    public static void tyhjendaListid (List<Integer> nimekiri) {
        Integer i = 0;
        for (i = 0; i != nimekiri.size(); i++) {
            nimekiri.remove(i);
        }
    }

    //////////
    // Põhiline lahendus
    /////////
    // Ülesanne 1: Leia mängunumbrite summa
    public static void m2ngudeSumma(FileReader sisend, Integer redInt, Integer greenInt, Integer blueInt) throws IOException {
        // Loo muutujad
        BufferedReader loe;
        String m2ng;
        String m2ngCropped;
        String[] voorud;
        String[] v2rvid;
        String[] v2rvidListi;
        Integer m2nguIdSumma = 0;
        Integer m2nguID = 0;
        List<Integer> punased = new ArrayList<Integer>();
        List<Integer> rohelised = new ArrayList<Integer>();
        List<Integer> sinised = new ArrayList<Integer>();

        // Loe fail
        try {
            loe = new BufferedReader(sisend);
            m2ng = loe.readLine();

            for (m2nguIdSumma = 0; m2ng != null; m2ng = loe.readLine()) {

                // Leia mängu ID ja trüki
                m2nguID = Integer.valueOf(kaheVahel(m2ng, "Game ", ":"));
                System.out.println("Loen mängu nr " + m2nguID + " andmeid.");

                // Edaspidise töötluse lihtsustamiseks eemaldan mängu ID osa.
                // Näide siit:
                // https://www.geeksforgeeks.org/remove-first-and-last-character-of-a-string-in-java/
                m2ngCropped = m2ng.substring(m2ng.indexOf(":") + 1);
                System.out.println("Mängu andmed :" + m2ngCropped);

                // Split näited siit:
                // https://www.geeksforgeeks.org/split-string-java-examples/
                //
                // Jaga rida "; " järgi tükkideks: ühe mängu voorud
                voorud = m2ngCropped.split("; ");

                // Jaga iga saadud lõik ", " järgi tükkideks: ühe vooru tulemused
                for (String a : voorud) {
                    System.out.println("Voor " + a);
                    v2rvid = a.split(", ");

                    for (String b : v2rvid) {
                        // Eemalda algusest ja lõpust tühikud
                        b = b.trim();
                        System.out.println("Värv: " + b);
                        v2rvidListi = b.split(" ");
                        if (v2rvidListi[1].equals("red")) {
                            punased.add(Integer.valueOf(v2rvidListi[0]));
                        }
                        else if (v2rvidListi[1].equals("green")) {
                            rohelised.add(Integer.valueOf(v2rvidListi[0]));
                        }
                        else if (v2rvidListi[1].equals("blue")) {
                            sinised.add(Integer.valueOf(v2rvidListi[0]));
                        }
                    }
                }

                // Trüki saadud listid
                System.out.println("Reds for game " + m2nguID + " :" + punased);
                System.out.println("Greens for game " + m2nguID +": " + rohelised);
                System.out.println("Blues for game " + m2nguID +": " + sinised);

                // Kontrolli andmeid
                if ((max(punased) <= redInt) && (max(rohelised) <= greenInt) && (max(sinised) <= blueInt)){
                    System.out.println("Game " + m2nguID + " applies. Adding ID to the sum.");
                    m2nguIdSumma = m2nguIdSumma + Integer.valueOf(m2nguID);
                    System.out.println("Sum of Game ID's is " + m2nguIdSumma);
                } else System.out.println("Game does not apply.");

                //System.out.println("Test-tekst enne listide tühjendamise funktsioone.");
               // Tühjenda listid
                punased.clear();
                rohelised.clear();
                sinised.clear();

                //System.out.println("Testprint empty lists: ");
                //System.out.println("Reds for game " + m2nguID + " :" + punased);
                //System.out.println("Greens for game " + m2nguID +": " + rohelised);
                //System.out.println("Blues for game " + m2nguID +": " + sinised);
            }

            System.out.println("Sum of games is " + m2nguIdSumma);

        } catch (IOException viga) {
            System.out.println("Tekkis viga");
        }
    }

    // Ülesanne 2:
    // https://adventofcode.com/2023/day/2#part2
    // Leia iga mängu kohta vähim igat värvi kuubikute arv, korruta need omavahel ja liida korrutised.
    public static void v2himateKorrutisteSumma(FileReader sisend) throws IOException {
    // Loo muutujad
    BufferedReader loe;
    String m2ng;
    String m2ngCropped;
    String[] voorud;
    String[] v2rvid;
    String[] v2rvidListi;
    Integer m2nguID = 0;
    Integer m2nguKorrutis = 0;
    Integer korrutisteSumma = 0;
    Integer vajalikPunane = 0;
    Integer vajalikRoheline = 0;
    Integer vajalikSinine = 0;
    List<Integer> punased = new ArrayList<Integer>();
    List<Integer> rohelised = new ArrayList<Integer>();
    List<Integer> sinised = new ArrayList<Integer>();

    // Loe fail
        try {
        loe = new BufferedReader(sisend);
        m2ng = loe.readLine();

        for (korrutisteSumma = 0; m2ng != null; m2ng = loe.readLine()) {

            // Leia mängu ID ja trüki
            m2nguID = Integer.valueOf(kaheVahel(m2ng, "Game ", ":"));
            System.out.println("Loen mängu nr " + m2nguID + " andmeid.");

            // Edaspidise töötluse lihtsustamiseks eemaldan mängu ID osa.
            // Näide siit:
            // https://www.geeksforgeeks.org/remove-first-and-last-character-of-a-string-in-java/
            m2ngCropped = m2ng.substring(m2ng.indexOf(":") + 1);
            System.out.println("Mängu andmed :" + m2ngCropped);

            // Split näited siit:
            // https://www.geeksforgeeks.org/split-string-java-examples/
            //
            // Jaga rida "; " järgi tükkideks: ühe mängu voorud
            voorud = m2ngCropped.split("; ");

            // Jaga iga saadud lõik ", " järgi tükkideks: ühe vooru tulemused
            for (String a : voorud) {
                System.out.println("Voor " + a);
                v2rvid = a.split(", ");

                for (String b : v2rvid) {
                    // Eemalda algusest ja lõpust tühikud
                    b = b.trim();
                    System.out.println("Värv: " + b);
                    v2rvidListi = b.split(" ");
                    if (v2rvidListi[1].equals("red")) {
                        punased.add(Integer.valueOf(v2rvidListi[0]));
                    }
                    else if (v2rvidListi[1].equals("green")) {
                        rohelised.add(Integer.valueOf(v2rvidListi[0]));
                    }
                    else if (v2rvidListi[1].equals("blue")) {
                        sinised.add(Integer.valueOf(v2rvidListi[0]));
                    }
                }
            }

            // Trüki saadud listid
            System.out.println("Reds for game " + m2nguID + " :" + punased);
            System.out.println("Greens for game " + m2nguID +": " + rohelised);
            System.out.println("Blues for game " + m2nguID +": " + sinised);

            // Leia iga värvi maksimumid ehk miinimum, mis on selle mängu jaoks vaja
            vajalikPunane = max(punased);
            vajalikRoheline = max(rohelised);
            vajalikSinine = max (sinised);
            System.out.println("Minimum amount of cubics needed for the game:");
            System.out.println("Reds: " + vajalikPunane);
            System.out.println("Greens: " + vajalikRoheline);
            System.out.println("Blues: " + vajalikSinine);

            m2nguKorrutis = vajalikPunane * vajalikRoheline * vajalikSinine;
            System.out.println("Power of this game is: " + m2nguKorrutis);

            korrutisteSumma = korrutisteSumma + m2nguKorrutis;

            // Tühjenda listid
            punased.clear();
            rohelised.clear();
            sinised.clear();

            //System.out.println("Testprint empty lists: ");
            //System.out.println("Reds for game " + m2nguID + " :" + punased);
            //System.out.println("Greens for game " + m2nguID +": " + rohelised);
            //System.out.println("Blues for game " + m2nguID +": " + sinised);
        }

        System.out.println("Sum of powers is: " + korrutisteSumma);

    } catch (IOException viga) {
        System.out.println("Tekkis viga");
    }

    }
}
