package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int money;
    private List<Card> cards = new ArrayList<>();

    public void drawCard(CardDeck cardDeck) {
       this.cards.add(cardDeck.drawCard());
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

}
