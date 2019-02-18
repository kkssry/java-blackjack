package javablackjack.blackjack.domain;

import javablackjack.blackjack.util.NumberManager;

import java.util.List;
import java.util.function.BiFunction;

public class Pair {
    private Player user;
    private Player dealer;
    private GameResult result = GameResult.DEFAULT;

    public Pair(Player user, Player dealer) {
        this.user = user;
        this.dealer = dealer;
    }

    public void releaseInitCards() {
        dealer.drawCard(BlackjackGame.CARD_DECK.drawCard());
        user.drawCard(BlackjackGame.CARD_DECK.drawCard());
        dealer.drawCard(BlackjackGame.CARD_DECK.drawCard());
        user.drawCard(BlackjackGame.CARD_DECK.drawCard());
    }

    public GameResult checkResultCase(List<BiFunction<Player, Player, GameResult>> cases) {
        for (BiFunction<Player, Player, GameResult> aCase : cases) {
            if (isResultDefault()) {
                result = aCase.apply(user, dealer);
                this.finishTurnCases();
            }
        }
        return result;
    }

    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(BlackjackGame.CARD_DECK.drawCard());
            checkResultCase(ResultCases.burstCase());
            whenUserTwentyScore_finishTurn(user.isBlackjack());
        }
        if (choiceNumber == 2) {
            user.finishTurn();
        }

    }

    private void whenUserTwentyScore_finishTurn(boolean isTwentyScore) {
        if (isTwentyScore) {
            user.finishTurn();
        }
    }

    private void finishTurnCases() {
        if (!this.isResultDefault()) {
            user.finishTurn();
        }
    }

    public boolean isResultDefault() {
        return result.equals(GameResult.DEFAULT);
    }

    public boolean isDealerGetCard() {
        return dealer.score() < NumberManager.DEALER_NUMBER;
    }

    public boolean isUserTurn() {
        return user.isTurn();
    }

    public Player getUser() {
        return user;
    }

    public Player getDealer() {
        return dealer;
    }

    public GameResult getResult() {
        return result;
    }

    public void dealerDrawCard(Card card) {
        dealer.drawCard(card);
    }


    public boolean isDealerTurn() {
        return dealer.isTurn();
    }

    public void finishDealerTurn() {
        dealer.finishTurn();
    }
}
