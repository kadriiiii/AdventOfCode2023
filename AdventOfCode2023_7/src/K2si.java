import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class K2si {
    private ArrayList<Kaart> k2teList = new ArrayList<Kaart>();
    String k2si;

    public ArrayList<Kaart> getK2si() {
        return k2teList;
    }

    public K2si(String k2si) {
        this.k2si = k2si;
        createK2si(k2si);
    }

    // Tühi konstruktor ka
    public K2si() {}

    // Selle osa peaksin ilmselt faili lugemisega asendama või oma failile kohandama
    public void createK2si(String kaardid) {
        k2teList.removeAll(k2teList);
        for (String part : kaardid.split("")) {
            Kaart currentCard = new Kaart(part);
            k2teList.add(currentCard);
        }
    }

    @Override
    public String toString() {
        return this.k2si.toString();
    }

    // Loe, mitu korda mingit kaarti on, et tuvastada nelikud, viisikud jne.
    public Map<KaardiV22rtus, Integer> checkFrequency(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = new HashMap<KaardiV22rtus, Integer>();
        for (Kaart c : k2si) {
            if (freqMap.containsKey(c.getV22rtus())) {
                freqMap.put(c.getV22rtus(), freqMap.get(c.getV22rtus()) + 1);
            } else {
                freqMap.put(c.getV22rtus(), 1);
            }
        }
        return freqMap;
    }

//    // Kui kaartide "tiheduskaardil" on ainult 1-d, siis on highCard
//    public boolean checkHighCard(ArrayList<Kaart> k2si) {
//        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
//        return !freqMap.containsValue(2) && !freqMap.containsValue(3)
//                && !freqMap.containsValue(4) && !freqMap.containsValue(5);
//    }

    // Kui kaartide "tiheduskaardil" on 2 ja ei ole 3, siis on paar.
    public boolean checkPair(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        return freqMap.containsValue(2) && !freqMap.containsValue(3);
    }

    // Kui kaartide "tiheduskaardil on 2x2, siis on kaks paari.
    public boolean checkTwoPair(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        return Collections.frequency(freqMap.values(), 2) == 2;
    }

    // Kui kaartide tiheduskaardil on 3 ja ei ole 2, siis on kolmik.
    public boolean checkThreeOfAKind(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        return freqMap.containsValue(3) && !freqMap.containsValue(2);
    }

    // Kui kaartide tiheduskaardil on 2 ja 3, siis on põdra maja
    public boolean checkFullHouse(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        Set<Integer> fullHouseCheck = new HashSet<Integer>(freqMap.values());
        //System.out.println(freqMap.keySet());
        return fullHouseCheck.contains(2) && fullHouseCheck.contains(3);
    }

    // Kui kaartide tiheduskaardil on 4, siis on nelik.
    public boolean checkFourOfAKind(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        return freqMap.containsValue(4);
    }

    // Kui kaartide tiheduskaardil on 5, siis on viisik.
    public boolean checkFiveOfAKind(ArrayList<Kaart> k2si) {
        Map<KaardiV22rtus, Integer> freqMap = checkFrequency(k2si);
        return freqMap.containsValue(5);
    }

    // Hinda kätt
    public K2eV22rtus evaluateHand(ArrayList<Kaart> k2si) {
        // Tugevamast nõrgemani
        if (checkFiveOfAKind(k2si)) {
            return K2eV22rtus.fiveOfAKind;
        } else if (checkFourOfAKind(k2si)) {
            return K2eV22rtus.fourOfAKind;
        } else if (checkFullHouse(k2si)) {
            return K2eV22rtus.fullHouse;
        } else if (checkThreeOfAKind(k2si)) {
            return K2eV22rtus.threeOfAKind;
        } else if (checkTwoPair(k2si)) {
            return K2eV22rtus.twoPairs;
        } else if (checkPair(k2si)) {
            return K2eV22rtus.onePair;
        } else {
            return K2eV22rtus.highCard;
        }

    }

}
