public class Kaart {
    // Defineerin kaardid
    private String card;
    private KaardiV22rtus value;

    // Konstruktorid
    public Kaart(){}
    public Kaart(KaardiV22rtus kv){
        this.value = kv;
        this.card = String.valueOf(kv.v22rtus);
    }
    public Kaart(String card) {
            this.card = card;
            this.value = convertV22rtus(this.card.substring(0, 1));
    }

    // Saa kaardi väärtus
    public KaardiV22rtus getV22rtus() {
        return value;
    }

    public KaardiV22rtus convertV22rtus(String v) {
        switch (v) {
            case "2":
                return KaardiV22rtus.Two;
            case "3":
                return KaardiV22rtus.Three;
            case "4":
                return KaardiV22rtus.Four;
            case "5":
                return KaardiV22rtus.Five;
            case "6":
                return KaardiV22rtus.Six;
            case "7":
                return KaardiV22rtus.Seven;
            case "8":
                return KaardiV22rtus.Eight;
            case "9":
                return KaardiV22rtus.Nine;
            case "T":
                return KaardiV22rtus.T;
            case "J":
                return KaardiV22rtus.J;
            case "Q":
                return KaardiV22rtus.Q;
            case "K":
                return KaardiV22rtus.K;
            case "A":
                return KaardiV22rtus.A;
            default:
                return null;
        }
    }

    public String getKaart() {
        return card;
    }
    @Override
    public String toString() {
        return this.card;
    }
}
