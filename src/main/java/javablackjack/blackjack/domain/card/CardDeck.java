package javablackjack.blackjack.domain.card;

import java.util.List;

public class CardDeck {
    public List<Card> cards;

    public CardDeck(List<Card> cards) {
       this.cards = cards;
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
