package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import javablackjack.blackjack.util.NumberManager;
import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class PairTest extends BaseTest {
    private static final Logger log = getLogger(PairTest.class);

    @Test
    public void HitOrStandMethodTest() {
        BlackjackGame blackjackGame = new BlackjackGame();

        Player player = new Player("peter");
        player.drawCard(new Card(Number.FIVE, CardPattern.DIAMOND));
        player.drawCard(new Card(Number.SIX, CardPattern.DIAMOND));

        Player dealer = new Player("dealer");

        blackjackGame.initUser(player, dealer);

        blackjackGame.getPair().userChoiceHitOrStand(1);
        log.debug("userScore : {}", player.score());

        softly.assertThat(dealer.isTurn()).isTrue();
        softly.assertThat(player.isTurn()).isFalse();
    }

    @Test
    public void ScoreTest() {
        if (21 > NumberManager.BLACKJACK_NUMBER) {
            log.debug("adfasdfas");
        }

    }
}