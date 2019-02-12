package javablackjack.blackjack.domain;

import java.util.*;

public class CardDeck {
    public List<Card> cards = new ArrayList<>();

    public CardDeck() {
        for (Number num : Number.values()) {
            addPattern(num);
        }
        Collections.shuffle(cards);
    }

    private void addPattern(Number num) {
        for (CardPattern value : CardPattern.values()) {
            cards.add(new Card(num, value));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        return cards.remove(0);
    }

}
