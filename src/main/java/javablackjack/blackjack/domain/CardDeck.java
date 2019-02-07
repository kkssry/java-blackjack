package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    public List<Card> cards = new ArrayList<>();

    public CardDeck() {
        for (int i = 0; i < 52; i++) {
            cards.add(new Card());
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
