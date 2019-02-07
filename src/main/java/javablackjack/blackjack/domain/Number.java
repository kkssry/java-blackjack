package javablackjack.blackjack.domain;

public enum Number {
    TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9), TEN("10", 10),
    A("A", 1), J("J", 10), Q("Q", 10), K("K", 10);
    private final String stringNum;
    private final int num;

    Number(String stringNum, int num) {
        this.stringNum = stringNum;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getStringNum() {
        return stringNum;
    }

    @Override
    public String toString() {
        return "Number{" +
                "stringNum='" + stringNum + '\'' +
                ", num=" + num +
                '}';
    }
}
