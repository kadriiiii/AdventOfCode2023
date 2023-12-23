import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static java.util.Collections.min;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("AdventOfCode2023_5_sisend.txt");
        // Ülesanne 1
        l2himLocation(sisendFail);
        // Ülesanne 2
        // kaardid(sisendFail);
    }

    /////////////////////////////////////////
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

    // Abifunktsioon tuvastamaks kas char on digit
    public static boolean kasOnDigit(char t2ht) {
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

        // Kas t2ht on listis
        return symboliteList.contains(t2ht);
    }


    ////////////////////////////////////////////
    //// Ülesanne 1
    /////////////////////////////////
    public static void l2himLocation(FileReader sisend) throws IOException {

        // Loe sisendfail Arraysse
        // Iga Array element on üks rida, sh tühjad read
        try {
            // Loe failist andmed
            List<String> andmed = loeFail(sisend);
            // System.out.println("Sisendandmed: ");
            // for (String s : andmed) {
            //    System.out.println(s);
            //}

            // Algandmed ehk seeds
            // Sisendi esimesel real
            String seedsAsString = andmed.get(0);
            // Võta eest "seeds: " ära ja
            String seedsAsStringCropped = seedsAsString.substring(seedsAsString.indexOf(":") + 1);
            // Eemalda üleliigsed tühikud
            seedsAsStringCropped = seedsAsStringCropped.trim();
            // Eralda seeds väärtused tühikute järgi
            List<String> seedsAsList = Arrays.asList(seedsAsStringCropped.split(" "));
            System.out.println("Seeds: " + seedsAsList);
            // Teisenda seeds BigIntegerideks,
            // sest selgus, et Integeride jaoks on liiga suured
            List<BigInteger> seeds = new ArrayList<BigInteger>();
            for (String s : seedsAsList) {
                s = s.trim();
                seeds.add(new BigInteger(s));
            }
            System.out.println("Seeds numbritena: " + seeds);

            // Lõppandmete hoiukoht
            // Hashtable'i asemel
            // Eeldus:
            // seeds.get(0) vastab l6ppTulemused.get(0) jne
            List<BigInteger> l6ppTulemused = new ArrayList<BigInteger>();
            // Pane seeds listi algandmeteks
            l6ppTulemused = seeds;

            ////////////////////////////////////////////////////////////////////
            // Üle kõikide seedide
            // Kontrolli andmemaatriksid ükshaaval
            // Kontrolli kõiki seede, kas mõni neist sobib selle reaga
            for (int seedIndex = 0; seedIndex < seeds.size(); seedIndex++) {
                // Loe andmemaatriks
                // Tühi list, kuhu lugeda maatrikseid
                List<String> maatriksiRead = new ArrayList<String>();
                // Iga seedi puhul algustan uuesti 4. reast
                Integer maatriksiAlgus = 4;
                // Kuni tühja reani
                // Igas sellises tsüklis peab muutuma ühele seedile
                // vastav koht listis l6ppTulemused
                for (int failiRida = maatriksiAlgus; failiRida < andmed.size(); failiRida = maatriksiAlgus ) {
                    System.out.println("Alustan maatriksi loomist realt: " + failiRida);
                    // Tühjenda maatriksilist
                    maatriksiRead.clear();
                    // Loe sisse üks maatriks
                    for (int andmeRida = failiRida; (andmed.get(andmeRida).trim().length() != 0 && andmeRida != andmed.size() - 2); andmeRida++) {
                        // Loe kõik maatriksi read listi
                        maatriksiRead.add(andmed.get(andmeRida));
                    }

                    for (int mitmesRida = 0; mitmesRida < maatriksiRead.size(); mitmesRida++) {
                        // Kontrolli, kas rea esimene sümbol on digit.
                        // Kui on, siis seda rida kontrollime seedi vastu.
                        if (kasOnDigit(maatriksiRead.get(mitmesRida).charAt(0))) {
                            String praeguneRida = maatriksiRead.get(mitmesRida);
                            //////// Tee reast BigIntegeride list
                            // Eemalda üleliigsed tühikud
                            praeguneRida = praeguneRida.trim();
                            // Eralda seeds väärtused tühikute järgi
                            List<String> praeguneRidaListina = new ArrayList<>();
                            praeguneRidaListina = Arrays.asList(praeguneRida.split(" "));
                            // Teisenda BigIntegeriks
                            List<BigInteger> praeguneRidaNumbritena = new ArrayList<BigInteger>();
                            for (String s : praeguneRidaListina) {
                                s = s.trim();
                                praeguneRidaNumbritena.add(new BigInteger(s));
                            }
                            System.out.println("Maatriksi rida " + mitmesRida + ": " + praeguneRidaNumbritena);

                            // Abisuurus - source ja lenght summa
                            BigInteger sourceJaLengthSumma = (praeguneRidaNumbritena.get(2)).add(praeguneRidaNumbritena.get(1));
                            // Võrdle l6ppTulemused vs maatriksiAndmed
                            // Kui l6ppTulemused.get(seedIndex on suurem või võrdne rea elemndiga 1
                            if ((l6ppTulemused.get(seedIndex).compareTo(praeguneRidaNumbritena.get(1)) == 1
                                    || l6ppTulemused.get(seedIndex).compareTo(praeguneRidaNumbritena.get(1)) == 0)
                                    && l6ppTulemused.get(seedIndex).compareTo(sourceJaLengthSumma) == -1 ) {
                                // Asenda l6ppTulemus(seedIndex),
                                // uus väärtus w on maatriksiAndmed(0) + y
                                // kus uutujaJasourciVaheon seed (või järgmine muutuja) - maatriksiAndmed(1)
                                BigInteger muutujaJaSourciVahe = (l6ppTulemused.get(seedIndex)).subtract(praeguneRidaNumbritena.get(1));
                                BigInteger uusMuutuja = (praeguneRidaNumbritena.get(0)).add(muutujaJaSourciVahe);

                                // Kopeeri vastav muutuja lõpptulemuste listist seedi
                                // (hilisemaks võrdlemiseks)
                                seeds.set(seedIndex, l6ppTulemused.get(seedIndex));
                                // Asenda vastav ÜKS value listis l6ppTulemused
                                l6ppTulemused.set(seedIndex, uusMuutuja);
                                System.out.println("Uuendatud lõpptulemused: " + l6ppTulemused);
                                break;
                            }
                        }
                    }

                    // Kui maatriksi läbimise jooksul ei leitud listi l6ppTulemused uut väärtust,
                    // siis peab seeds ja l6ppTulemused vastav väärtus sama olema.

                    System.out.println("Sisend: " + seeds);
                    System.out.println("Väljund: " + l6ppTulemused);

                    maatriksiAlgus = maatriksiAlgus + maatriksiRead.size() + 3;
                    // Järgmise maatriksi juurde
                }
            }

            System.out.println("Lõpptulemused: " + l6ppTulemused);

            // Lähim location
            BigInteger l2himLocation = Collections.min(l6ppTulemused);
            // Vastus 388071289
            System.out.println("Lähim asukoht (location): " + l2himLocation);

        } catch (Exception viga) {
            System.out.println(viga);
        }
    }

}