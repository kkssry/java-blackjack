package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.CardDeck;
import com.codesquad.blackjack.domain.cases.BurstCase;
import com.codesquad.blackjack.domain.cases.ResultCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public class Pair {
    private static final Logger log = LogManager.getLogger(Pair.class);

    private User user;
    private Dealer dealer;

    public Pair(User user, Dealer dealer) {
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
            whenUserTwentyOneScore_finishTurn(user.isBlackjack());
        }

        if (choiceNumber == 2) {
            user.finishTurn();
        }
        return checkResultCase(new BurstCase());
    }

    private void whenUserTwentyOneScore_finishTurn(boolean isTwentyOneScore) {
        if (isTwentyOneScore) {
            user.finishTurn();
        }

    }

    public void finishTurnCases(GameResult result) {
        if (!this.isResultDefault(result)) {
            finishPlayerTurn();
        }
    }

    //////////////////////////////////////////////////////////
    public boolean isUserBurst() {
        return user.isBurst();
    }

    public boolean isDealerBurst() {
        return dealer.isBurst();
    }

    public boolean isPush() {
        return user.isPush(dealer);
    }


    public boolean isUserWin() {
        return user.isWin(dealer);
    }

    public boolean isDealerWin() {
        return dealer.isWin(user);
    }

    /////////////////////////////

    public boolean isResultDefault(GameResult result) {
        return result.equals(GameResult.DEFAULT);
    }

    public boolean isDealerGetCard() {
        return dealer.isDrawCard();
    }

    public boolean isUserTurn() {
        return user.isTurn();
    }

    public User getUser() {
        return user;
    }

    public Player getDealer() {
        return dealer;
    }

    public void dealerDrawCard(Card card) {
        dealer.drawCardWithDealerTurn(card);
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

  /*  public void bettingChip(Chip bettingChip) {
        user.loseChip(bettingChip);
    }*/

    public void increaseChip(GameResult gameResult ) {
        log.debug(gameResult);

        if (gameResult == GameResult.USER_WIN) {
            user.winningChip();
            return;
        }
        if (gameResult == GameResult.PUSH) {
            user.pushChip();
            return;
        }
        if (gameResult == GameResult.BLACKJACK_USER_WIN) {
            user.blackjackChip();
            return;
        }

        user.loseChip();
    }


}