package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import javablackjack.blackjack.util.NumberManager;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class PairTest extends BaseTest {
    private static final Logger log = getLogger(PairTest.class);

    @Test
    public void HitOrStandMethodTest() {
        List<Card> aCard = new ArrayList<>();

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(aCard));

        Player player = new Player("peter");
        player.drawCard(new Card(Number.FIVE, CardPattern.DIAMOND));
        player.drawCard(new Card(Number.SIX, CardPattern.DIAMOND));

        Player dealer = new Player("dealer");

        blackjackGame.initUser(player, dealer);

        aCard.add(new Card(Number.K,CardPattern.CLOVER));
        blackjackGame.getPair().userChoiceHitOrStand(1,new CardDeck(aCard));
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