package javablackjack.blackjack.domain.player;

import javablackjack.blackjack.domain.card.Card;
import javablackjack.blackjack.util.NumberManager;

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
