package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.CardDeck;
import com.codesquad.blackjack.domain.cases.BlackjackCase;
import com.codesquad.blackjack.domain.cases.BurstCase;
import com.codesquad.blackjack.domain.cases.WinnerCase;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.Pair;
import com.codesquad.blackjack.domain.player.User;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class
BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Pair pair;
    private CardDeck cardDeck; // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다.

    public BlackjackGame(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public BlackjackGame initUser(User user, Dealer dealer) {
        pair = new Pair(user, dealer);
        return this;
    }

    public BlackjackGame startGame(Chip bettingChip) {
        pair.bettingChip(bettingChip);
        pair.releaseInitCards(cardDeck);
        return this;
    }

    public GameResult checkBlackjack() {
        return pair.checkResultCase(new BlackjackCase());
    }

    public GameResult winner() {
        System.out.println();
        System.out.println("--------- 최종결과 ---------");
        return pair.checkResultCase(new WinnerCase());
    }

    public GameResult dealerTurn() {
        //todo  플레이어게 위임
        if (pair.isDealerGetCard()) {
            pair.dealerDrawCard(cardDeck.drawCard());
        }

        if (!pair.isDealerGetCard()) {
            pair.finishPlayerTurn();
        }

        return pair.checkResultCase(new BurstCase());
    }

    public void manageChip(GameResult gameResult, Chip bettingChip) {
        pair.increaseChip(gameResult, bettingChip);
    }

    public GameResult userChoiceHitOrStand(int choiceNumber) {
        return pair.userChoiceHitOrStand(choiceNumber, cardDeck);
    }


    public boolean isUserTurn() {
        return pair.isUserTurn();
    }

    public boolean isDealerTurn() {
        return pair.isDealerTurn();
    }

    public Pair getPair() {
        return pair;
    }

    public void playerTurnFinish(GameResult result) {
        pair.finishTurnCases(result);
    }
}
