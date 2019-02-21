package javablackjack.blackjack.domain;

import javablackjack.blackjack.domain.cases.BurstCase;
import javablackjack.blackjack.domain.cases.ResultCases;
import javablackjack.blackjack.util.NumberManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Pair {
    private static final Logger log = LogManager.getLogger(Pair.class);

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
        //todo player  상속하는 dealer 객채 생성
        if (isDealerGetCard()) {
            dealer.drawCard(card);
        }
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

    public void bettingChip(Chip bettingChip) {
        user.betting(bettingChip);
    }

    public void increaseChip(GameResult gameResult, Chip chip) {
        log.debug(gameResult);

        Map<GameResult, Function<Chip, Chip>> cases = new HashMap<>();
        cases.put(GameResult.USER_WIN, chip1 -> user.winningChip(chip1));
        cases.put(GameResult.PUSH, chip1 -> user.pushChip(chip1));
        cases.put(GameResult.BLACKJACK_USER_WIN, chip1 -> user.blackjackChip(chip1));

        log.debug(cases.get(gameResult));
        if (cases.containsKey(gameResult)) {
            cases.get(gameResult).apply(chip);

        }
    }


}