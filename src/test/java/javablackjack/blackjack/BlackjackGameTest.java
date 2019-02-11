package javablackjack.blackjack;

import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGameTest extends BaseTest {
    private static final Logger log = getLogger(BlackjackGameTest.class);

    @Test
    public void 딜러셋업() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.initUser("skull");
        blackjackGame.startGame();
        blackjackGame.UserChoiceHitOrStand(2);
        blackjackGame.dealerTurn();
        log.debug("딜러숫자 합 : {}", blackjackGame.dealerScore());
        softly.assertThat(blackjackGame.isDealerTurnFinish()).isTrue();
        log.debug("딜러버스트여부 : {}", blackjackGame.isDealerBurst());
    }
}