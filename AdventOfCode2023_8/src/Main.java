import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Ülesande andmed
        FileReader sisendFail = new FileReader("..\\sisend\\AdventOfCode2023_8_sisend.txt");
        // Näiteandmed
        // Ülesanne 1
        //FileReader sisendFail = new FileReader("AdventOfCode2023_8_n2iteAndmed_1.txt");
        //FileReader sisendFail = new FileReader("AdventOfCode2023_8_n2iteAndmed_2.txt");
        // Ülesanne 1
        //mituSammu1(sisendFail);
        // Ülesanne 2
        //FileReader sisendFail = new FileReader("AdventOfCode2023_8_n2iteAndmed_3.txt");
        mituSammu2(sisendFail);
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

    // Abimeetod kahe arvu suurima ühine nimetaja leidmiseks (GCD)
    private static long gcd(long a, long b) {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    // Abimeetod vähima ühise kordaja leidmiseks (LCM)
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    // Abimeetod array LCM jaoks
    private static long lcm_array(ArrayList<Integer> s) {
        long result = Long.valueOf(s.get(0));
        for (int i = 1; i < s.size(); i++) {
            result = lcm(result, s.get(i));
        }
        return result;
    }

    //////////////// Ülesanne 2
    public static void mituSammu2(FileReader sisend) {
        try {
            // Loe sisendandmed
            List<String> andmed = loeFail(sisend);

            // Jaga andmed juhisteks ja koordinaatideks
            ArrayList<String> juhised = new ArrayList<String>(Arrays.asList(andmed.get(0).split("")));
            System.out.println("Juhised: " + juhised);

            Map<String, ArrayList<String>> koordinaadid = new HashMap<String, ArrayList<String>>();

            // Alustan koordinaatide lugemist realt number 2
            for (int i = 2; i < andmed.size(); i++) {
                String[] temp = andmed.get(i).split(" = ");
                // Vasakul pool võrdusmärki on key
                String key_temp = temp[0];
                // Paremal pool on value
                // Eemaldan sulud
                String value_temp = temp[1].substring(temp[1].indexOf("(")+1, temp[1].indexOf(")"));
                ArrayList<String> values_temp = new ArrayList<String>(Arrays.asList(value_temp.split(", ")));
                // Lisan key ja value HashMapi
                koordinaadid.put(key_temp, values_temp);
            }

            System.out.println("Koordinaadid: " + koordinaadid);

            // Esimese key viimane täht peab olema A
            // Panen sellised eraldi listi "nodes"
            ArrayList<String> nodes = new ArrayList<String>();
            for (int i = 0; i < koordinaadid.size(); i++) {
                String koordinaat = String.valueOf(koordinaadid.keySet().toArray()[i]);
                if (koordinaat.endsWith("A")) {
                    nodes.add(koordinaat);
                }
            }
            System.out.println("A-dega lõppevaid keysid on " + nodes.size());
            System.out.println("Need on: " + nodes);

            // Sammude kogumise array
            ArrayList<Integer> nodes_sammud = new ArrayList<Integer>();

            // Kõigepealt leian iga A-ga lõppeva jaoks sammude arvu.
            // Selleks, et leida nende sammude ühisosa.
            for (int k = 0; k < nodes.size(); k++) {
                String key = nodes.get(k);
                // Juhiste koordinaat
                Integer j = 0;
                // Sammude lugeja
                Integer sammud = 0;
                do {
                    if (juhised.get(j).equals("L")) {
                        key = koordinaadid.get(key).get(0);
                        sammud++;
                    }
                    if (juhised.get(j).equals("R")) {
                        key = koordinaadid.get(key).get(1);
                        sammud++;
                    }
                    j++;
                    if (j == juhised.size()) {
                        j = 0;
                    }
                } while (!key.endsWith("Z"));

                System.out.println("Samme oli: " + sammud);
                nodes_sammud.add(sammud);
            }
            // Kui on vaja kontrolliks sammud välja printida
            //System.out.println(nodes_sammud);

            // Leian sammude least common multiplieri
            // https://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
            long LCM = lcm_array(nodes_sammud);
            System.out.println("Sammude " + nodes_sammud + " väikseim ühine kordaja on: " +  LCM);
            // 12315788159977

        } catch (Exception viga) {
            System.out.println(viga);
        }
    }


    //////////////// Ülesanne 1
    public static void mituSammu1(FileReader sisend) {
        try {
            // Loe sisendandmed
            List<String> andmed = loeFail(sisend);

            // Jaga andmed juhisteks ja koordinaatideks
            ArrayList<String> juhised = new ArrayList<String>(Arrays.asList(andmed.get(0).split("")));
            System.out.println("Juhised: " + juhised);

            Map<String, ArrayList<String>> koordinaadid = new HashMap<String, ArrayList<String>>();

            // Alustan koordinaatide lugemist realt number 2
            for (int i = 2; i < andmed.size(); i++) {
                String[] temp = andmed.get(i).split(" = ");
                // Vasakul pool võrdusmärki on key
                String key_temp = temp[0];
                // Paremal pool on value
                // Eemaldan sulud
                String value_temp = temp[1].substring(temp[1].indexOf("(")+1, temp[1].indexOf(")"));
                ArrayList<String> values_temp = new ArrayList<String>(Arrays.asList(value_temp.split(", ")));
                // Lisan key ja value HashMapi
                koordinaadid.put(key_temp, values_temp);
            }

            System.out.println("Koordinaadid: " + koordinaadid);

            // Esimene key peab olema AAA
            String key = "AAA";
            // Juhiste koordinaat
            Integer j = 0;
            // Sammude lugeja
            Integer sammud = 0;
            do {
                if (juhised.get(j).equals("L")) {
                    key = koordinaadid.get(key).get(0);
                    sammud++;
                }
                if (juhised.get(j).equals( "R")) {
                    key = koordinaadid.get(key).get(1);
                    sammud++;
                }
                j++;
                if (j == juhised.size()) {
                    j = 0;
                }
            } while (!key.equals("ZZZ"));

            System.out.println("Samme oli: " + sammud);
            // 18113


        } catch (Exception viga) {
            System.out.println(viga);
        }
    }
}
