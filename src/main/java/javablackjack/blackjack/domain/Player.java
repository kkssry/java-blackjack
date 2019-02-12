package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private int money = 20;
    private List<Card> cards = new ArrayList<>();
    private String name;


    public
    Player(String userName) {
        this.name = userName;
    }

    public void drawCard(Card card) {
        this.cards.add(card);
    }

    public int showScore() {
        if (this.isBurst()) {
            setOneA();
        }
        return score();
    }

    public int score() {
        return cards.stream().mapToInt(Card::getNum).sum();

    }

    public List<Card> getCards() {
        return cards;
    }

    public int hasMoney() {
        return money;
    }

    public String getName() {
        return name;
    }


    public boolean isBurst() {
        //에이를 가지고 있니?
        // - 10;
        return score() > 21;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public boolean compareScore(Player dealer) {
        if (this.isBurst()) {
            if (showScore() > dealer.score()) {
                return true;
            }
            return false;
        }
        return showScore() > dealer.score();
    }

    public void setOneA() {
        if (isElevenA()) {
            cards.get(cards.indexOf(Card.ELEVEN_A)).setIsOneA();
        }
    }

    public boolean isElevenA() {
        return cards.contains(Card.ELEVEN_A);
    }

    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", cards=" + cards +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isBlackjack() {
        return score()==21;
    }
}
