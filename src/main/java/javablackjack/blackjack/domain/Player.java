package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int money = 20;
    private List<Card> cards = new ArrayList<>();
    private String name;

    public Player(String userName) {
        this.name = userName;
    }

    public void drawCard(CardDeck cardDeck) {
        this.cards.add(cardDeck.drawCard());
    }

    public int score() {
        return cards.stream().mapToInt(Card::getNum).sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int hasMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", cards=" + cards +
                ", name='" + name + '\'' +
                '}';
    }
}
