package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private int money = 20;
    //todo : 1급 객체로 리팩토링 해야함.
    private List<Card> cards = new ArrayList<>();
    private String name;



    public Player(String userName) {
        this.name = userName;
    }

    public void drawCard(Card card) {
        this.cards.add(card);
        setOneA();
    }

    public int score() {
        return cards.stream().mapToInt(Card::getNum).sum();

    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }


    public boolean isBurst() {
        return score() > 21;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public boolean compareScore(Player otherPlayer) {
//        //setOneA();
//        if (otherPlayer.isBlackjack()) {
//
//        }
        return score() > otherPlayer.score();
    }

    public void setOneA() {
        if (this.isBurst() && isElevenA()) {
            cards.get(cards.indexOf(Card.ELEVEN_A)).setIsOneA();
        }
    }

    public boolean isElevenA() {
        return cards.contains(Card.ELEVEN_A);
    }

    public boolean isBlackjack() {
        return score() == 21;
    }

//    public boolean checkUserTurn() {
//        if (isBlackjack()) {
//            return userTurn = false;
//        }
//        if (isBurst()) {
//            return userTurn = false;
//        }
//        return userTurn;
//    }

    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", cards=" + cards +
                ", name='" + name + '\'' +
                '}';
    }
}
