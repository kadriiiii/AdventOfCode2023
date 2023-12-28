import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Ülesande andmed
        FileReader sisendFail = new FileReader("..\\sisend\\AdventOfCode2023_7_sisend.txt");
        // Näiteandmed
        //FileReader sisendFail = new FileReader("AdventOfCode2023_7_n2iteAndmed.txt");
        // Ülesanne 1 ja 2
        // waysToBeatTheRecord(sisendFail);
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
}