public enum KaardiV22rtus {
    // Kaartide väärtused
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    T(10),
    // Ülesanne 1 jaoks on J = 11
    // J(11),
    // Ülesanne 2 jaoks on J = 1
    J(1),
    Q(12),
    K(13),
    A(14);
    int v22rtus;
        KaardiV22rtus(int v) {
            v22rtus = v;
        }
    int getV22rtus(){
        return v22rtus;
    }
}
