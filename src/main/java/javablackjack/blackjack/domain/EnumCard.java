package javablackjack.blackjack.domain;

public enum EnumCard {
    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10),
    A("A",1), J("J",10), Q("Q",10), K("K", 10);
    private final String stringNum;
    private final int num;
    private String a;

    EnumCard(String stringNum, int num) {
        this.stringNum = stringNum;
        this.num = num;
    }

    public EnumCard seta(String a) {
        this.a = a;
        return this;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "EnumCard{" +
                "stringNum='" + stringNum + '\'' +
                '}';
    }
}
