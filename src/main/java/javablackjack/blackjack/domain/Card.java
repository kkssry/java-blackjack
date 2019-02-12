package javablackjack.blackjack.domain;

import java.util.Objects;

public class Card {
    public static final Card ELEVEN_A = new Card(Number.A,CardPattern.SPADE);
    private Number num;
    private CardPattern pattern;
    private boolean isOneA = false;

    public Card(Number num, CardPattern pattern) {
        this.num = num;
        this.pattern = pattern;
    }

    public int getNum() {
        if (isOneA) {
            return 1;
        }
        return num.getNum();
    }

    public boolean isOneA() {
        return isOneA;
    }

    public void setIsOneA() {
        this.isOneA = true;
    }

    @Override
    public String toString() {
        return "Card{" +
                "num=" + num +
                ", pattern=" + pattern +
                '}';
    }

    public String getStringNum() {
        return num.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return isOneA == card.isOneA &&
                num == card.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, isOneA);
    }
}
