package com.codesquad.blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {
    public static CardDeck create() {
        List<Card> cards = new ArrayList<>();
        for (Number num : Number.values()) {
            cards.addAll(addPatten(num));
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> addPatten(Number num) {
        List<Card> cards = new ArrayList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            cards.add(new Card(num, cardPattern));
        }
        return cards;
    }
}
