import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Ülesande andmed
        FileReader sisendFail = new FileReader("..\\sisend\\AdventOfCode2023_7_sisend.txt");
        // Näiteandmed
        //FileReader sisendFail = new FileReader("AdventOfCode2023_7_n2iteAndmed.txt");
        // Ülesanded 1 ja 2
        v6itudeSumma(sisendFail);
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

    // Abimeetod käte vahetamiseks
    public static void vaheta(ArrayList<K2si> k2teNimekiri, Integer j2rjekorraNumber) {
        K2si temp = k2teNimekiri.get(j2rjekorraNumber);
        k2teNimekiri.set(j2rjekorraNumber, k2teNimekiri.get(j2rjekorraNumber + 1));
        k2teNimekiri.set(j2rjekorraNumber + 1, temp);
    }

    // Abimeetod punktide vahetamiseks
    public static void vahetaPunktid(ArrayList<Integer> punktideNimekiri, Integer j2rjekorraNumber) {
        Integer temp = punktideNimekiri.get(j2rjekorraNumber);
        punktideNimekiri.set(j2rjekorraNumber, punktideNimekiri.get(j2rjekorraNumber + 1));
        punktideNimekiri.set(j2rjekorraNumber + 1, temp);
    }

    // Abimeetod J-iga käe suurima väärtuse leidmiseks
    public static K2eV22rtus suurimK2eV22rtus(K2si k2si) {
        // Jaota käsi kaartideks
        ArrayList<Kaart> kaardid = k2si.getK2si();
        ArrayList<Kaart> kaardid_temp = new ArrayList<>( k2si.getK2si());
        // Kontrolli kaartide esinemise kordi
        Map kaartideTihedus = k2si.checkFrequency(kaardid);
        // Käe väärtuse kohahoidja, kõige madalam väärtus
        K2eV22rtus V22rtus = K2eV22rtus.highCard;
        K2eV22rtus temp = V22rtus;
        if (kaartideTihedus.containsKey(KaardiV22rtus.J)) {
            // Üle kõigi kaardiväärtuste
            for (KaardiV22rtus kv : KaardiV22rtus.values()) {
                for (int i = 0; i < 5; i++) {
                    if (kaardid.get(i).getV22rtus() == KaardiV22rtus.J) {
                        kaardid_temp.set(i, new Kaart(kv));
                        //System.out.println("Kaardid temp: " + kaardid_temp);
                    }
                }
                //temp = V22rtus;
                V22rtus = k2si.evaluateHand(kaardid_temp);
                if (V22rtus.v22rtus > temp.v22rtus) {
                    temp = V22rtus;
                }

            }
        } else {
            temp = k2si.evaluateHand(kaardid);
        }
            return temp;
    }


    //////////////// Ülesanne 1 ja 2
    public static void v6itudeSumma(FileReader sisend) {
        try {
            // Loe sisendandmed
            List<String> andmed = loeFail(sisend);

            // Jaga andmed käteks ja punktideks
            ArrayList<Integer> punktid = new ArrayList<Integer>();
            ArrayList<K2si> k2ed = new ArrayList<K2si>();

            for (String s : andmed) {
                List<String> rida = new ArrayList<String>();
                rida = Arrays.asList(s.split(" "));
                // Lisa rea teine väärtus punktide listi
                punktid.add(Integer.valueOf(rida.get(1)));
                // System.out.println(punktid);
                // Konverteeri rea esimene väärtus käeks
                K2si k2si = new K2si(rida.get(0));
                k2ed.add(k2si);
                // System.out.println(k2ed);
            }

            System.out.println("Käed: " + k2ed);

//            // Kontrolli iga käe kohta, mis ta on
//            for (int i = 0; i < k2ed.size(); i++) {
//                K2si k2si = k2ed.get(i);
//                //System.out.println(k2si);
//                Map kaartideTihedus = k2si.checkFrequency(k2si.getK2si());
//                //System.out.println(kaartideTihedus);
//                K2eV22rtus v = k2si.evaluateHand(k2si.getK2si());
//                //System.out.println(v);
//            }

            // Bubble sort kaartide väärtuste järgi
            Integer size = k2ed.size();
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    K2si A = k2ed.get(j);
                    K2si B = k2ed.get(j + 1);
                    K2eV22rtus svA = suurimK2eV22rtus(A);
                    K2eV22rtus svB = suurimK2eV22rtus(B);
                    //System.out.println("Käsi j " + A + ", väärtusega " + svA.v22rtus);
                    //System.out.println("Käsi j+1 " + B + ", väärtusega " + svB.v22rtus);
                    // Kuna mul on kaks erinevat listi,
                    // üks käte ja teine punktide jaoks,
                    // siis pean punktide listi järjekorda ka eraldi muutma.
                    // (St mapiga oleks lihtsam ilmselt)

//                    //////////////// Ülesanne 1
//                    // Kui käsi j on suurem kui käsi j+1,
//                    // siis vaheta kohad ära
//                    if (svA.v22rtus > svB.v22rtus) {
//                        vaheta(k2ed, j);
//                        vahetaPunktid(punktid, j);
//                        System.out.println("Vahetasin " + A + " ja " + B);
//                    }
//                    ////////////////// Ülesande 1 spetsiifilise osa lõpp


                    //////////////// Ülesanne 2
                    //System.out.println(A + " väärtus on " + svA);
                    //System.out.println(B + " väärtus on " + svB);
                    if (svA.v22rtus > svB.v22rtus) {
                        vaheta(k2ed, j);
                        vahetaPunktid(punktid, j);
                        //System.out.println("Vahetasin " + A + " ja " + B);
                    }
                }
            }

            //System.out.println("EEL-Sorteeritud käed: " + k2ed);

            // Teine bubble sort, sest ühes tsüklis kirjutades läks midagi sassi.
            // Kui käed on võrdsed, siis kontrolli üksikute kaartide väärtust, alates esimesest.
            //
            for (int m = 0; m < size - 1; m++) {
                for (int n = 0; n < size - m- 1; n++) {
                    K2si A = k2ed.get(n);
                    K2si B = k2ed.get(n + 1);
                    K2eV22rtus svA = suurimK2eV22rtus(A);
                    K2eV22rtus svB = suurimK2eV22rtus(B);
                    //System.out.println("Käsi j " + A + ", väärtusega " + AV22rtus.v22rtus);
                    //System.out.println("Käsi j+1 " + B + ", väärtusega " + BV22rtus.v22rtus);
                    // Kuna mul on kaks erinevat listi,
                    // üks käte ja teine punktide jaoks,
                    // siis pean punktide listi järjekorda ka eraldi muutma.
                    // (St mapiga oleks lihtsam ilmselt)

                    // Defineerin kõik käe kaardid ükshaaval, et selge oleks.
                    Kaart a0 = A.getK2si().get(0);
                    KaardiV22rtus a0V22rtus = a0.convertV22rtus(a0.getKaart());
                    Kaart a1 = A.getK2si().get(1);
                    KaardiV22rtus a1V22rtus = a1.convertV22rtus(a1.getKaart());
                    Kaart a2 = A.getK2si().get(2);
                    KaardiV22rtus a2V22rtus = a2.convertV22rtus(a2.getKaart());
                    Kaart a3 = A.getK2si().get(3);
                    KaardiV22rtus a3V22rtus = a3.convertV22rtus(a3.getKaart());
                    Kaart a4 = A.getK2si().get(4);
                    KaardiV22rtus a4V22rtus = a4.convertV22rtus(a4.getKaart());

                    Kaart b0 =B.getK2si().get(0);
                    KaardiV22rtus b0V22rtus = b0.convertV22rtus(b0.getKaart());
                    Kaart b1 =B.getK2si().get(1);
                    KaardiV22rtus b1V22rtus = b1.convertV22rtus(b1.getKaart());
                    Kaart b2 =B.getK2si().get(2);
                    KaardiV22rtus b2V22rtus = b2.convertV22rtus(b2.getKaart());
                    Kaart b3 =B.getK2si().get(3);
                    KaardiV22rtus b3V22rtus = b3.convertV22rtus(b3.getKaart());
                    Kaart b4 =B.getK2si().get(4);
                    KaardiV22rtus b4V22rtus = b4.convertV22rtus(b4.getKaart());

                    // alates esimesest kaartist
                    //
                    //////////////// 1. kaart
                    // Kui "ülemise" käe 1. kaart on juba väiksem kui "alumise" oma,
                    // ära tee midagi.
                    if (svA.v22rtus != svB.v22rtus)
                    {
                        continue;
                    }

                    if(a0V22rtus.v22rtus < b0V22rtus.v22rtus){
                        continue;
                    } else
                    if ( a0V22rtus.v22rtus > b0V22rtus.v22rtus) {
                        vaheta(k2ed, n);
                        vahetaPunktid(punktid, n);
                        //System.out.println("Vahetasin käed, sest " + a0 + " on suurem kui " + b0);
                        // Kui käe A 1. kaart võrdub käe B omaga, mine järgmise kaardi juurde.
                        // Ehk et selle kohta if-i ei ole.
                        ////////////////// 2. kaart
                        // Kui käe A 2. kaart on suurem kui käe B oma,
                        // siis muuda käte asukohad ära.

                    } else if (a1V22rtus.v22rtus < b1V22rtus.v22rtus) {
                        continue;
                    } else if (a1V22rtus.v22rtus > b1V22rtus.v22rtus) {
                        vaheta(k2ed, n);
                        vahetaPunktid(punktid, n);
                        //System.out.println("Vahetasin käed, sest " + a1 + " on suurem kui " + b1);

                        ///////////////////// 3. kaart
                        // Kui käe A 3. kaart on suurem kui käe B oma,
                        // siis muuda käte asukohad ära.

                    } else if (a2V22rtus.v22rtus < b2V22rtus.v22rtus) {
                        continue;
                    } else if (a2V22rtus.v22rtus > b2V22rtus.v22rtus) {
                        vaheta(k2ed, n);
                        vahetaPunktid(punktid, n);
                        //System.out.println("Vahetasin käed, sest " + a2 + " on suurem kui " + b2);

                        //////////////////// 4. kaart
                        // Kui käe A 4. kaart on suurem kui käe B oma,
                        // siis muuda käte asukohad ära.

                    } else if (a3V22rtus.v22rtus < b3V22rtus.v22rtus) {
                        continue;
                    } else if (a3V22rtus.v22rtus > b3V22rtus.v22rtus) {
                        vaheta(k2ed, n);
                        vahetaPunktid(punktid, n);
                        //System.out.println("Vahetasin käed, sest " + a3 + " on suurem kui " + b3);

                        ////////////////////// 5. kaart
                        // Kui käe A 5. kaart on suurem kui käe B oma,
                        // siis muuda käte asukohad ära.

                    } else if ( a4V22rtus.v22rtus < b4V22rtus.v22rtus) {
                        continue;
                    } else if ( a4V22rtus.v22rtus > b4V22rtus.v22rtus) {
                        vaheta(k2ed, n);
                        vahetaPunktid(punktid, n);
                        //System.out.println("Vahetasin käed, sest " + a4 + " on suurem kui " + b4);

                    }
                }
            }

            System.out.println("Sorteeritud käed: " + k2ed);
            System.out.println("Punktid: " + punktid);

            // Punktide summa
            Integer summa = 0;
            //System.out.println("Punkte on: " + punktid.size());
            for (int p = 0; p < punktid.size(); p++) {
                Integer vahesumma = punktid.get(p) * (p+1);
                summa = summa + vahesumma;
            }
            System.out.println("Punkte kokku: " + summa);
            // 246795406 Ül 1
            // 249356515 Ül 2

            } catch (Exception viga) {
            System.out.println(viga);
        }
    }
}

