package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;

import java.util.List;

public interface Player {
    int score();

    void drawCard(Card card);

    String getName();

    List<Card> getCards();
}
