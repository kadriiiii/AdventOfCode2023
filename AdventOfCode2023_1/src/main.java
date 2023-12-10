import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Advent of Code 2023
// Ülesanne 1: https://adventofcode.com/2023/day/1


public class main {
	
	public static void main(String[] args) throws IOException {
		FileReader sisendFail = new FileReader("AdventOfCode2023_1_sisend.txt");
		
		// Ülesanne 1A
		// leiaKalibratsiooniParameeter(sisendFail);
		
		// Ülesanne 1B
		leiaT2ielikKalibratsiooniParameeter(sisendFail);
	}
		

	/*************************************************************/
	// Ülesanne 1 A
	/*************************************************************/
	// Leiab esialgse kalibratsiooniparameetri, mis võtab arvesse ainult numbriliselt kirjutatud numbreid (digits)
	public static void leiaKalibratsiooniParameeter (FileReader sisendFail) throws IOException {
		
		BufferedReader loe;

			try {
				loe = new BufferedReader(sisendFail);
				String rida = loe.readLine();
				Integer kalibratsiooniParameetriteSumma = 0;

				while (rida != null) {
					// System.out.println("Loen rida: " + rida);
					// Numbrid stringina
					String numbrid= rida.replaceAll("[^0-9]", "");
					// System.out.println("Leidsin numbrid: " + numbrid);
					// Esimene ja viimane number kokku
					String yksikKalibratsiooniParameeterTekstina = new StringBuilder().append(numbrid.charAt(0))
				    											.append(numbrid.charAt(numbrid.length() - 1))
				    											.toString();
					// Kirjuta kalibratsiooniparameeter välja
					// System.out.println("Selle rea kalibratsiooniparameeter on: " + yksikKalibratsiooniParameeterTekstina);
						// Esimene ja viimane number kokku numbriks
				    try {
			            Integer yksikKalibratsiooniParameeter = Integer.parseInt(yksikKalibratsiooniParameeterTekstina);
						// System.out.println("Selle rea kalibratsiooniparameeter numbrina on: " + yksikKalibratsiooniParameeter);
						// Lisa saadud parameeter kalibratsiooniparameetrite summale
						kalibratsiooniParameetriteSumma = kalibratsiooniParameetriteSumma + yksikKalibratsiooniParameeter;
						}
			        catch(NumberFormatException nfe) {
			            System.out.println("Exception " + nfe);
			        	}				
				    // Kirjuta kalibratsiooniparameetrite summa
				    // System.out.println("Kalibratsiooniparameetrite summa on: " + kalibratsiooniParameetriteSumma);
						// Loe järgmine rida
					rida = loe.readLine();
				}
				System.out.println("Kalibratsiooniparameetrite summa on: " + kalibratsiooniParameetriteSumma);
				loe.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	/*************************************************************/
	// Ülesanne 1 B
	/*************************************************************/
	// Leiab täiendatud kalibratsiooniparameetri, mis võtab arvesse ka sõnadena kirjutatud numbrid.
	// Nüanss - lihtne replace ei tööta, sest osad numbrid jagavad tähti, näiteks "eightwo".
	public static void leiaT2ielikKalibratsiooniParameeter (FileReader sisendFail) throws IOException {
		
		BufferedReader loe;

		try {
			loe = new BufferedReader(sisendFail);
			String rida = loe.readLine();
			Integer kalibratsiooniParameetriteSumma = 0;

			while (rida != null) {
				System.out.println("Loen rida: " + rida);
				// Tee rida väiksetäheliseks, igaks juhuks
				String rida_v2ike = rida.toLowerCase();
				
				// Kontrolli, kas reas on numbreid kas sõna või numbrina.
				// Sorteeri leitud numbrid asukohtade järgi.
				kalibratsiooniParameetriteSumma = kalibratsiooniParameetriteSumma + s6naraamat(rida);

				System.out.println("Kalibratsiooniparameetrite summa on: " + kalibratsiooniParameetriteSumma);
				// Loe järgmine rida
				rida = loe.readLine();
			}
			System.out.println("Kalibratsiooniparameetrite summa on: " + kalibratsiooniParameetriteSumma);
			loe.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	// Kaart selleks, et tekitada key-value paarid täiendatud kalibratsiooniparameetri leidmiseks
	public static Integer s6naraamat (String s6na) {

		HashMap<String, Integer> numbriteV22rtused = new HashMap<>();
		HashMap<Integer, Integer> numbriteAsukohad = new HashMap<>();
		
		// Sisesta andmed, mida igast reast otsida
		numbriteV22rtused.put("null", 0);
		numbriteV22rtused.put("zero", 0);
		numbriteV22rtused.put("one", 1);
		numbriteV22rtused.put("two", 2);
		numbriteV22rtused.put("three", 3);
		numbriteV22rtused.put("four", 4);
		numbriteV22rtused.put("five", 5);
		numbriteV22rtused.put("six", 6);
		numbriteV22rtused.put("seven", 7);
		numbriteV22rtused.put("eight", 8);
		numbriteV22rtused.put("nine", 9);
		numbriteV22rtused.put("0",  0);
		numbriteV22rtused.put("1", 1);
		numbriteV22rtused.put("2", 2);
		numbriteV22rtused.put("3", 3);
		numbriteV22rtused.put("4", 4);
		numbriteV22rtused.put("5", 5);
		numbriteV22rtused.put("6", 6);
		numbriteV22rtused.put("7", 7);
		numbriteV22rtused.put("8", 8);
		numbriteV22rtused.put("9", 9);

		// Leia iga key-valu paari kohta, kas see Key on reas olemas.
		// Kui on, siis pane leitud numbri _väärtus_ ja tema asukoht uude HashMapi nimega numbriteAsukohad.
		// Uues HashMapis Key on asukoht ja Value on numbri väärtus (mõlemad integerid).
	    for (String i: numbriteV22rtused.keySet()) {
	    	Integer uusOtsinguAlgus = 0;
	    	Integer asukoht = 0;
	    	do {
	    		asukoht =  s6na.indexOf(i,uusOtsinguAlgus);
	    		if (asukoht > -1)
	    			numbriteAsukohad.put(asukoht, numbriteV22rtused.get(i));
	    		uusOtsinguAlgus = asukoht + 1;
	    	}
	    	while (asukoht > -1);
	    	}
	           
	    // Kirjuta numbrite asukohtade tabel selle sõna jaoks
	    System.out.println(numbriteAsukohad); 
	    
	    // Kirjuta numbrite asukohtade tabel sorteerituna 
	    HashMap<Integer, Integer> numbridAsukohtadeJ2rgi = sorteeriV22rtusteJ2rgi(numbriteAsukohad);
	    System.out.println(numbridAsukohtadeJ2rgi); 
	
	    // Konverdi sorteeritud HashMapi (numbridAsukohtadeJ2rgi) key väärtused arrayks
	    ArrayList<Integer> numbrid = new ArrayList<Integer>(numbridAsukohtadeJ2rgi.values());

	    Integer esimeneNumber = 0;
	    Integer viimaneNumber = 0; 

	    // Leia listi esimene ja viimane key
	    if (numbrid.size() > 0) {
	    	esimeneNumber = numbrid.get(0);
	    	System.out.println("Esimene number (value): " + esimeneNumber);
	               
	    	viimaneNumber = numbrid.get(numbrid.size() - 1);
	        System.out.println("Viimane number (value): " + viimaneNumber);
	    }
	    
	    // Liida esimene ja viimane stringidena kokku
	    String yksikKalibratsiooniParameeterTekstina = esimeneNumber.toString() + viimaneNumber.toString();
	    Integer yksikKalibratsiooniParameeter = 0;
	    
		// Kirjuta kalibratsiooniparameeter välja
		// System.out.println("Selle rea kalibratsiooniparameeter on: " + yksikKalibratsiooniParameeterTekstina);
		// Esimene ja viimane number kokku numbriks
	    try {
            yksikKalibratsiooniParameeter = Integer.parseInt(yksikKalibratsiooniParameeterTekstina);
			System.out.println("Selle rea kalibratsiooniparameeter numbrina on: " + yksikKalibratsiooniParameeter);
	    }
        catch(NumberFormatException nfe) {
            System.out.println("Exception " + nfe);
        	}	
        
	    return yksikKalibratsiooniParameeter;
} 
	           
	           
	
	// Sorteeri numbrite asukohtade tabel väärtuste (asukohtade) järgi
	// Vt: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
	// Siin tabelis
	//	Key = asukoht, mille järgi saame esimese ja viimase numbri
	// 	Value = number, mida on kalibratsiooniparameetri jaoks vaja
	public static HashMap<Integer, Integer> sorteeriV22rtusteJ2rgi(HashMap<Integer, Integer> kaart) {
		
		// Loo list kaardi andmetest
	    List<Map.Entry<Integer, Integer> > nimekiri = new LinkedList<Map.Entry<Integer, Integer> >(kaart.entrySet());       
	        	   
	    // Sorteeri
	    Collections.sort(nimekiri,
	                   (i1, i2) -> i1.getKey().compareTo(i2.getKey()));
	    
	    // Pane andmed uuesti HashMapi, sest ma ei oska listi siit väljastada (proovisin)
        HashMap<Integer, Integer> sorteeritud = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> kaardijupp : nimekiri) {
            sorteeritud.put(kaardijupp.getKey(), kaardijupp.getValue());
        }
        return sorteeritud;
    }
}
	           