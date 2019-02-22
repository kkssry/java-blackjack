package javablackjack.blackjack.domain.card;

public enum CardPattern {
    SPADE("♠"), CLOVER("♣"), DIAMOND("◇"), HEART("♡");

    private String icon;

    CardPattern(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
