package javablackjack.blackjack.domain;

import javablackjack.blackjack.io.OutputResult;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Pair pair;
    protected static CardDeck CARD_DECK = new CardDeck(); // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다.

    public void initUser(Player player, Player dealer) {
        pair = new Pair(player, dealer);
    }

    public void startGame() {
        pair.releaseInitCards();
    }

    public String checkBlackjack() {
        return pair.checkResultCase(ResultCases.blackjackCase()).getGameResult();
    }

    public void winner() {
        System.out.println();
        System.out.println("--------- 최종결과 ---------");
        pair.checkResultCase(ResultCases.winnerCaseWithUserStay());
    }

    public void dealerTurn() {
        if (pair.isDealerGetCard()) {
           pair.dealerDrawCard(CARD_DECK.drawCard());
        }
        if (!pair.isDealerGetCard()) {
            pair.finishDealerTurn();
        }

        pair.checkResultCase(ResultCases.burstCase());
    }

    public void userChoiceHitOrStand(int choiceNumber) {
        pair.userChoiceHitOrStand(choiceNumber);
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
}
