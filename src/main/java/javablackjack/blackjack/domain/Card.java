package javablackjack.blackjack.domain;

public class Card {
    private Number num;
    private CardPattern pattern;

    public Card(Number num, CardPattern pattern) {
        this.num = num;
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Card{" +
                "num=" + num +
                ", pattern=" + pattern +
                '}';
    }
}
