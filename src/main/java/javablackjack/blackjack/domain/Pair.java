package javablackjack.blackjack.domain;

import javablackjack.blackjack.domain.cases.BurstCase;
import javablackjack.blackjack.domain.cases.ResultCases;
import javablackjack.blackjack.util.NumberManager;

import java.util.function.Function;

public class Pair {
    private Player user;
    private Player dealer;

    public Pair(Player user, Player dealer) {
        this.user = user;
        this.dealer = dealer;
    }

    public void releaseInitCards(CardDeck cardDeck) {
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
    }

    public GameResult checkResultCase(ResultCases resultCases) {
        GameResult result = GameResult.DEFAULT;
        for (Function<Pair, GameResult> aCase : resultCases.caseList()) {
            if (isResultDefault(result)) {
                result = aCase.apply(this);
            }
        }
        return result;
    }


    public GameResult userChoiceHitOrStand(int choiceNumber, CardDeck cardDeck) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());

            whenUserTwentyScore_finishTurn(user.isBlackjack());

        }

        if (choiceNumber == 2) {
            user.finishTurn();
        }


        return checkResultCase(new BurstCase());
    }

    private void whenUserTwentyScore_finishTurn(boolean isTwentyScore) {
        if (isTwentyScore) {
            user.finishTurn();
        }

    }

    public void finishTurnCases(GameResult result) {
        if (!this.isResultDefault(result)) {
            finishPlayerTurn();
        }
    }


    ///////////////////////

    public boolean push() {
        if (user.isTurn()) {
            return user.isBlackjack() == dealer.isBlackjack();
        } else {
            return user.score() == dealer.score();
        }

    }

    /////////////////////////////

    public boolean isResultDefault(GameResult result) {
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

    public void dealerDrawCard(Card card) {
        dealer.drawCard(card);
    }


    public boolean isDealerTurn() {
        return dealer.isTurn();
    }

    public void finishPlayerTurn() {
        if (user.isTurn()) {
            user.finishTurn();
        }

        if (!user.isTurn()) {
            dealer.finishTurn();
        }
    }

}