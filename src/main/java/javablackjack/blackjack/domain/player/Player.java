package javablackjack.blackjack.domain.player;

import javablackjack.blackjack.domain.card.Card;

import java.util.List;

public interface Player {
    int score();
    void drawCard(Card card);
    String getName();
    List<Card> getCards();
}
