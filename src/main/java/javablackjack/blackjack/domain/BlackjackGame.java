package javablackjack.blackjack.domain;

import javablackjack.blackjack.domain.cases.BlackjackCase;
import javablackjack.blackjack.domain.cases.BurstCase;
import javablackjack.blackjack.domain.cases.WinnerCase;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Pair pair;
    private CardDeck cardDeck; // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다.

    public BlackjackGame(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void initUser(Player player, Player dealer) {
        pair = new Pair(player, dealer);
    }

    public void startGame() {
        pair.releaseInitCards(cardDeck);
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
