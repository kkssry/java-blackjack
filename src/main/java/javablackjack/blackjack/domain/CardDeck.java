package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    public List<EnumCard> cards = new ArrayList<>();

    public CardDeck() {
        for (EnumCard card : EnumCard.values()) {
            cards.add(card.seta("클로버"));
            cards.add(card.seta("다이아"));
            cards.add(card.seta("하트"));
            cards.add(card.seta("스페이드"));
        }
    }

    public List<EnumCard> getCards() {
        return cards;
    }

    public void setCards(List<EnumCard> cards) {
        this.cards = cards;
    }
}
