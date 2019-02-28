package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.util.NumberManager;

public class Dealer extends AbstractPlayer {

    public Dealer(String name) {
        super(name);
    }

    public boolean isDrawCard() {
        return this.score() < NumberManager.DEALER_NUMBER;
    }

    public void drawCardWithDealerTurn(Card card) {
        if (this.isDrawCard()) {
            this.drawCard(card);
        }
    }
}
