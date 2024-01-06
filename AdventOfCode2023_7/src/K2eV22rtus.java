public enum K2eV22rtus {
    highCard(1), // Kõik kaardid on erinevad
    onePair(2), // Two cards of the same value.
    twoPairs(3), // Two different pairs.
    threeOfAKind(4), // Three cards of the same value.
    fullHouse(5), // Three of a kind and a pair.
    fourOfAKind(6), // Four cards of the same value.
    fiveOfAKind(7); // Five cards are of the same value.

    int v22rtus;
    K2eV22rtus(int v) {
        v22rtus = v;
    }
}
