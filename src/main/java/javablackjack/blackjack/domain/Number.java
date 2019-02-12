package javablackjack.blackjack.domain;

public enum Number {
    //string 빼기
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    A(11), J(10), Q(10), K(10);
    private final int num;

    Number(int num) {
        this.num = num;
    }

    public int isSpecialA(boolean isA) {
        return isA? 1 : 11;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Number{" +
                "stringNum='" + this.name() + '\'' +
                ", num=" + num +
                '}';
    }
}
