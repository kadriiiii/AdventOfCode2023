import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static java.util.Arrays.sort;
import static java.util.Collections.min;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader sisendFail = new FileReader("..\\sisend\\AdventOfCode2023_5_sisend.txt");
        // Ülesanne 1
        // l2himLocation(sisendFail);
        // Ülesanne 2
        l2himLocation2(sisendFail);
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

    // Abifunktsioon maatriksi andmete lugemiseks
    public static ArrayList<List<String>> looMaatriksid(List<String> failiRead, int reaNumber) {
        // Tühi list, kuhu lugeda maatriksi read
        List<String> maatriksiRead = new ArrayList<String>();
        // Tühi list, kuhu lisada maatriksid
        ArrayList<List<String>> maatriksid = new ArrayList<List<String>>();
        // Lisa esimene maatriks listi
        maatriksid.add(maatriksiRead);

        for (int rida = reaNumber; rida < failiRead.size(); rida++) {
            // Kui ei ole tühi rida ega numbritega rida
            if (!failiRead.get(rida).isEmpty()
                    && !kasOnDigit(failiRead.get(rida).charAt(0))) {
                // Prindi välja andmete nimi
                //System.out.println("Kaart: " + failiRead.get(rida));
            } else
                // Kui esimene "täht" on number,
                // lisa rida listi maatriksiRead
                if (!failiRead.get(rida).isEmpty()
                    && kasOnDigit(failiRead.get(rida).charAt(0))) {
                    maatriksiRead.add(failiRead.get(rida));
                } else if (failiRead.get(rida).isEmpty()) {
                    // Kui on järgmine tühi rida, siis
                    // lisa uus rida
                    rida++;
                    maatriksiRead = new ArrayList<String>();
                    // Lisa maatriks listi
                    maatriksid.add(maatriksiRead);
                }
        }
        return maatriksid;
    }

    // Siit: https://www.geeksforgeeks.org/java-program-to-find-closest-number-in-array/
    // Method to compare which one is the more close
    // We find the closest by taking the difference
    //  between the target and both values. It assumes
    // that val2 is greater than val1 and target lies
    // between these two.
    public static ArrayList<BigInteger> leiaL2himMin(ArrayList<BigInteger> arr, BigInteger target)
    {
        // x0 on esialgne "minimaalne vahe" minu arvu ja listiarvu vahel
        BigInteger x0 = new BigInteger("99999999999");
        // Väljundiks oleva Array kaks väärtust on alguses nullid
        ArrayList<BigInteger> minNaaber = new ArrayList<BigInteger>();
        minNaaber.add(BigInteger.valueOf(0));
        minNaaber.add(BigInteger.valueOf(0));
        // i + 2 sp, et paarituarvulised seedid on lengthid selles ülesandes
        Integer i;
        for (i = 0; i < arr.size(); i = i + 2) {
            BigInteger x = target.subtract(arr.get(i));
            if (target.compareTo(arr.get(i)) >= 0) {
                if (x.compareTo(x0) < 0) {
                    x0 = x;
                    minNaaber.set(0, arr.get(i));
                    minNaaber.set(1, BigInteger.valueOf(i));
                }
            }
        }
        return minNaaber;
    }

    // Leia lähim suurem naaber
    public static ArrayList<BigInteger> leiaL2himMax(ArrayList<BigInteger> arr, BigInteger target)
    {
        // x0 on esialgne "minimaalne vahe" minu arvu ja listiarvu vahel
        BigInteger x0 = new BigInteger("0");
        // Väljundiks oleva Array kaks väärtust on alguses nullid
        ArrayList<BigInteger> maxNaaber = new ArrayList<BigInteger>();
        maxNaaber.add(BigInteger.valueOf(0));
        maxNaaber.add(BigInteger.valueOf(0));
        // i + 2 sp, et paarituarvulised seedid on lengthid selles ülesandes
        Integer i;
        for (i = 0; i < arr.size(); i = i + 2) {
            BigInteger x = target.subtract(arr.get(i));
            if (target.compareTo(arr.get(i)) <= 0) {
                if (x.compareTo(x0) < 0) {
                    x0 = x;
                    maxNaaber.set(0, arr.get(i));
                    maxNaaber.set(1, BigInteger.valueOf(i));
                }
            }
        }
        return maxNaaber;
    }

        ////////////////////////////////////////
        // Ülesanne 2
        ////////////////////////////////////////
        public static void l2himLocation2(FileReader sisend) throws IOException {

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
                String seedsAsString = andmed.getFirst();
                // Võta eest "seeds: " ära ja
                String seedsAsStringCropped = seedsAsString.substring(seedsAsString.indexOf(":") + 1);
                // Eemalda üleliigsed tühikud
                seedsAsStringCropped = seedsAsStringCropped.trim();
                // Eralda seeds väärtused tühikute järgi
                List<String> seedsAsList = Arrays.asList(seedsAsStringCropped.split(" "));
                // System.out.println("Seeds: " + seedsAsList);
                // Teisenda seeds BigIntegerideks,
                ArrayList<BigInteger> seedsAtFirst = new ArrayList<BigInteger>();
                for (String s : seedsAsList) {
                    s = s.trim();
                    seedsAtFirst.add(new BigInteger(s));
                }
                //System.out.println("seedsAtFirst: " + seedsAtFirst);

                // Muuda seeds sedasi, et
                // seeds[0], seeds[0] + seeds[1], seeds[2], seeds[2] + seeds[3], ...
                ArrayList<BigInteger> seeds = new ArrayList<BigInteger>();

                //////////// Esimene otsiring kõikide seedidega
                for (int n = 0; n < seedsAtFirst.size(); n = n + 2) {
                    BigInteger seedN = seedsAtFirst.get(n);
                    //System.out.println("Arvutan seedi: " + seedN);
                    BigInteger nPlussYks = seedN.add(seedsAtFirst.get(n + 1)).subtract(BigInteger.valueOf(1));
                    //System.out.println("nPlussYks: " + nPlussYks);
                    BigInteger vahe = nPlussYks.subtract(seedN);
                    // System.out.println("Vahe: " + vahe);

                    for (int m = 0;
                         BigInteger.valueOf(m).compareTo(vahe) <= 0; m++) {
                         BigInteger uusSeed = seedN.add(BigInteger.valueOf(m));
                         seeds.add(uusSeed);
                    }
                }

                System.out.println("Uued seedid: " + seeds);


                // Loe "kaardid"
                // Stringidena
                // System.out.println("Loe maatriksid.");
                ArrayList<List<String>> kaardid = new ArrayList<List<String>>();
                kaardid = looMaatriksid(andmed, 3);
                // System.out.println("Maatriksite list: " + kaardid);

                // Loo stringide kujul olevatest maatriksitest
                // KaardiRida objektide list
                ArrayList<List<KaardiRida>> kaardidNumbritena = new ArrayList<List<KaardiRida>>();
                List<KaardiRida> yksMaatriksListis = new ArrayList<KaardiRida>();
                kaardidNumbritena.add(yksMaatriksListis);

                // Loo KaardiRida objektid
                // Üle kõigi maatriksite (st listide, mis listis on)
                for (int maatriksiJ2rjekorranumber = 0; maatriksiJ2rjekorranumber < kaardid.size(); maatriksiJ2rjekorranumber++) {
                    // Loo töödeldava maatriksi jaoks ajutine hoiukoht
                    List<String> ajutineMaatriks = new ArrayList<String>();
                    // Lisa ajutisse maatriksisse String kujul
                    // maatriks nr maatriksiJ2rjekorranumber
                    ajutineMaatriks = kaardid.get(maatriksiJ2rjekorranumber);
                    // System.out.println("Ajutine maatriks: " + ajutineMaatriks);
                    // Üle kõigi ridade, konverteeri selle maatriksi read objektideks KaardiRida
                    // listis kaardidNumbritena

                    for (int maatriksiReaNumber = 0; maatriksiReaNumber < ajutineMaatriks.size(); maatriksiReaNumber++) {
                        KaardiRida rida =  new KaardiRida(ajutineMaatriks.get(maatriksiReaNumber));
                        yksMaatriksListis.add(rida);
                    }
                    // Kui üks maatriks on töödeldud, loo järgmine ajutineMaatriks kohahoidja
                    yksMaatriksListis = new ArrayList<KaardiRida>();
                    // Lisa äsja kirjeldatud rida maatriks listi
                    kaardidNumbritena.add(yksMaatriksListis);
                }


                // Lõppandmete hoiukoht
                // Kohatäitjaks "seeds" väärtused
                List<BigInteger> l6ppTulemused = new ArrayList<BigInteger>();
                l6ppTulemused = seeds;

                ////////////////////////////////////////////////////////////////////
                // Üle kõikide seedide
                // Kontrolli andmemaatriksid ükshaaval
                // Kontrolli kõiki seede, kas mõni neist sobib selle reaga
                for (int seedIndex = 0; seedIndex < seeds.size(); seedIndex++) {
                    // Võrdle l6ppTulemused vs maatriksiAndmed
                    // Üle iga kõigi maatriksite
                    for (int maatriksMidaVaatan = 0; maatriksMidaVaatan < kaardidNumbritena.size(); maatriksMidaVaatan++) {
                        // Üle selle maatriksi kõigi ridade
                        for (int ridaMidaVaatan = 0; ridaMidaVaatan < kaardidNumbritena.get(maatriksMidaVaatan).size(); ridaMidaVaatan++) {
                            // Kui l6ppTulemused.get(seedIndex on suurem või võrdne rea elemndiga 1
                            KaardiRida temp = kaardidNumbritena.get(maatriksMidaVaatan).get(ridaMidaVaatan);
                            // System.out.println("Maatriks nr " + maatriksMidaVaatan + " ja rida nr " + ridaMidaVaatan);
                            // System.out.println("Selle rea andmed: dest " + temp.destination + " src " + temp.source + " l " + kaardidNumbritena.get(maatriksMidaVaatan).get(ridaMidaVaatan).length);
                            if ((l6ppTulemused.get(seedIndex).compareTo(temp.source) == 1
                                    || l6ppTulemused.get(seedIndex).compareTo(temp.source) == 0)
                                    && (l6ppTulemused.get(seedIndex).compareTo(temp.sourceEnd) == -1
                                    || l6ppTulemused.get(seedIndex).compareTo(temp.sourceEnd) == 0)) {
                                // Asenda l6ppTulemus(seedIndex),
                                BigInteger uusMuutuja = temp.fromSourceToDestination(seeds.get(seedIndex));

                                // Kopeeri vastav muutuja lõpptulemuste listist seedi
                                // (hilisemaks võrdlemiseks)
                                seeds.set(seedIndex, l6ppTulemused.get(seedIndex));
                                // Asenda vastav ÜKS value listis l6ppTulemused
                                l6ppTulemused.set(seedIndex, uusMuutuja);
                                // System.out.println("Uuendatud lõpptulemused: " + l6ppTulemused);
                                break;
                            }
                        }
                    }

                    // Kui maatriksi läbimise jooksul ei leitud listi l6ppTulemused uut väärtust,
                    // siis peab seeds ja l6ppTulemused vastav väärtus sama olema
                    //System.out.println("Pärast seed " + seedIndex + " arvutamist.");
                    //System.out.println("Sisend: " + seeds);
                    //System.out.println("Väljund: " + l6ppTulemused);

                }

                System.out.println("Väljund: " + l6ppTulemused);

                // Lähim location
                BigInteger l2himLocation = Collections.min(l6ppTulemused);
                System.out.println("Lähim asukoht (location): " + l2himLocation);


            } catch (Exception viga) {
                System.out.println(viga);
            }

        }

        ////////////////////////////////////////////
        //// Ülesanne 1
        /////////////////////////////////
//    public static void l2himLocation(FileReader sisend) throws IOException {
//
//        // Loe sisendfail Arraysse
//        // Iga Array element on üks rida, sh tühjad read
//        try {
//            // Loe failist andmed
//            List<String> andmed = loeFail(sisend);
//            // System.out.println("Sisendandmed: ");
//            // for (String s : andmed) {
//            //    System.out.println(s);
//            //}
//
//            // Algandmed ehk seeds
//            // Sisendi esimesel real
//            String seedsAsString = andmed.get(0);
//            // Võta eest "seeds: " ära ja
//            String seedsAsStringCropped = seedsAsString.substring(seedsAsString.indexOf(":") + 1);
//            // Eemalda üleliigsed tühikud
//            seedsAsStringCropped = seedsAsStringCropped.trim();
//            // Eralda seeds väärtused tühikute järgi
//            List<String> seedsAsList = Arrays.asList(seedsAsStringCropped.split(" "));
//            System.out.println("Seeds: " + seedsAsList);
//            // Teisenda seeds BigIntegerideks,
//            // sest selgus, et Integeride jaoks on liiga suured
//            List<BigInteger> seeds = new ArrayList<BigInteger>();
//            for (String s : seedsAsList) {
//                s = s.trim();
//                seeds.add(new BigInteger(s));
//            }
//            // System.out.println("Seeds numbritena: " + seeds);
//
//            // Lõppandmete hoiukoht
//            // Hashtable'i asemel
//            // Eeldus:
//            // seeds.get(0) vastab l6ppTulemused.get(0) jne
//            List<BigInteger> l6ppTulemused = new ArrayList<BigInteger>();
//            // Pane seeds listi algandmeteks
//            l6ppTulemused = seeds;
//
//            ////////////////////////////////////////////////////////////////////
//            // Üle kõikide seedide
//            // Kontrolli andmemaatriksid ükshaaval
//            // Kontrolli kõiki seede, kas mõni neist sobib selle reaga
//            for (int seedIndex = 0; seedIndex < seeds.size(); seedIndex++) {
//                // Loe andmemaatriks
//                // Tühi list, kuhu lugeda maatrikseid
//                List<String> maatriksiRead = new ArrayList<String>();
//                // Iga seedi puhul algustan uuesti 4. reast
//                Integer maatriksiAlgus = 4;
//                // Kuni tühja reani
//                // Igas sellises tsüklis peab muutuma ühele seedile
//                // vastav koht listis l6ppTulemused
//                for (int failiRida = maatriksiAlgus; failiRida < andmed.size(); failiRida = maatriksiAlgus) {
//                    System.out.println("Alustan maatriksi loomist realt: " + failiRida);
//                    // Tühjenda maatriksilist
//                    maatriksiRead.clear();
//                    // Loe sisse üks maatriks
//                    for (int andmeRida = failiRida; (andmed.get(andmeRida).trim().length() != 0 && andmeRida != andmed.size() - 2); andmeRida++) {
//                        // Loe kõik maatriksi read listi
//                        maatriksiRead.add(andmed.get(andmeRida));
//                    }
//
//                    for (int mitmesRida = 0; mitmesRida < maatriksiRead.size(); mitmesRida++) {
//                        // Kontrolli, kas rea esimene sümbol on digit.
//                        // Kui on, siis seda rida kontrollime seedi vastu.
//                        if (kasOnDigit(maatriksiRead.get(mitmesRida).charAt(0))) {
//                            String praeguneRida = maatriksiRead.get(mitmesRida);
//                            //////// Tee reast BigIntegeride list
//                            // Eemalda üleliigsed tühikud
//                            praeguneRida = praeguneRida.trim();
//                            // Eralda seeds väärtused tühikute järgi
//                            List<String> praeguneRidaListina = new ArrayList<>();
//                            praeguneRidaListina = Arrays.asList(praeguneRida.split(" "));
//                            // Teisenda BigIntegeriks
//                            List<BigInteger> praeguneRidaNumbritena = new ArrayList<BigInteger>();
//                            for (String s : praeguneRidaListina) {
//                                s = s.trim();
//                                praeguneRidaNumbritena.add(new BigInteger(s));
//                            }
//                            System.out.println("Maatriksi rida " + mitmesRida + ": " + praeguneRidaNumbritena);
//
//                            // Abisuurus - source ja lenght summa
//                            BigInteger sourceJaLengthSumma = (praeguneRidaNumbritena.get(2)).add(praeguneRidaNumbritena.get(1));
//                            // Võrdle l6ppTulemused vs maatriksiAndmed
//                            // Kui l6ppTulemused.get(seedIndex on suurem või võrdne rea elemndiga 1
//                            if ((l6ppTulemused.get(seedIndex).compareTo(praeguneRidaNumbritena.get(1)) == 1
//                                    || l6ppTulemused.get(seedIndex).compareTo(praeguneRidaNumbritena.get(1)) == 0)
//                                    && l6ppTulemused.get(seedIndex).compareTo(sourceJaLengthSumma) == -1) {
//                                // Asenda l6ppTulemus(seedIndex),
//                                // uus väärtus w on maatriksiAndmed(0) + y
//                                // kus uutujaJasourciVaheon seed (või järgmine muutuja) - maatriksiAndmed(1)
//                                BigInteger muutujaJaSourciVahe = (l6ppTulemused.get(seedIndex)).subtract(praeguneRidaNumbritena.get(1));
//                                BigInteger uusMuutuja = (praeguneRidaNumbritena.get(0)).add(muutujaJaSourciVahe);
//
//                                // Kopeeri vastav muutuja lõpptulemuste listist seedi
//                                // (hilisemaks võrdlemiseks)
//                                seeds.set(seedIndex, l6ppTulemused.get(seedIndex));
//                                // Asenda vastav ÜKS value listis l6ppTulemused
//                                l6ppTulemused.set(seedIndex, uusMuutuja);
//                                System.out.println("Uuendatud lõpptulemused: " + l6ppTulemused);
//                                break;
//                            }
//                        }
//                    }
//
//                    // Kui maatriksi läbimise jooksul ei leitud listi l6ppTulemused uut väärtust,
//                    // siis peab seeds ja l6ppTulemused vastav väärtus sama olema.
//
//                    System.out.println("Sisend: " + seeds);
//                    System.out.println("Väljund: " + l6ppTulemused);
//
//                    maatriksiAlgus = maatriksiAlgus + maatriksiRead.size() + 3;
//                    // Järgmise maatriksi juurde
//                }
//            }
//
//            System.out.println("Lõpptulemused: " + l6ppTulemused);
//
//            // Lähim location
//            BigInteger l2himLocation = Collections.min(l6ppTulemused);
//            // Vastus 388071289
//            System.out.println("Lähim asukoht (location): " + l2himLocation);
//
//        } catch (Exception viga) {
//            System.out.println(viga);
//        }
//    }



}

class KaardiRida {
    // Konstruktorid
    // Default
    public KaardiRida() {}
    // Tavaline
    public KaardiRida(String algAndmeteRida) {
        // Eemalda üleliigsed tühikud
        algAndmeteRida = algAndmeteRida.trim();
        // Eralda seeds väärtused tühikute järgi
        List<String> algAndmeteRidaListina = new ArrayList<>();
        algAndmeteRidaListina = Arrays.asList(algAndmeteRida.split(" "));
        // Teisenda BigIntegeriks
        List<BigInteger> algAndmedNumbritena = new ArrayList<BigInteger>();
        for (String s : algAndmeteRidaListina) {
            s = s.trim();
            algAndmedNumbritena.add(new BigInteger(s));
        }

        // Defineeri objecti parameetrid
        destination = algAndmedNumbritena.get(0);
        source = algAndmedNumbritena.get(1);
        length = algAndmedNumbritena.get(2);
        sourceEnd = findSourceEnd(source, length);
        //thisSourceDestination = fromSourceToDestination(source, destination);
    }

    // Algandmetest
    // Selles järjekorras nagu sisendandmetes on
    public BigInteger source;
    public BigInteger destination;
    public BigInteger length;
    // Arvutatud
    public BigInteger sourceEnd;
    public BigInteger thisSourceDestination;

    // Meetodid
    public BigInteger findSourceEnd (BigInteger source, BigInteger length) {
        sourceEnd = (source.add(length)).subtract(BigInteger.valueOf(1));
        return sourceEnd;
    }

    public BigInteger fromSourceToDestination (BigInteger seed) {
        // Selle piirkonna source ja destiniation vahe, mis tuleb igale source'ile liita, et destination saada
        BigInteger vahe = destination.subtract(source);
        thisSourceDestination = seed.add(vahe);
        return thisSourceDestination;
    }
}