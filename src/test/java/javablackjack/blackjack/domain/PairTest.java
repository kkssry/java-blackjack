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
    public void player_win_increase_Chip() {
        Player player = new Player("peter");
        Player dealer = new Player("dealer");

        Pair pair = new Pair(player,dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.USER_WIN, new Chip(200));
        softly.assertThat(player.getChip().getChip()).isEqualTo(700);
    }

    @Test
    public void player_blackjack_increase_Chip() {
        Player player = new Player("peter");
        Player dealer = new Player("dealer");

        Pair pair = new Pair(player,dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.BLACKJACK_USER_WIN, new Chip(200));
        softly.assertThat(player.getChip().getChip()).isEqualTo(800);
    }

    @Test
    public void dealer_blakcjack_win_increase_Chip() {
        Player player = new Player("peter");
        Player dealer = new Player("dealer");

        Pair pair = new Pair(player,dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.BLACKJACK_DEALER_WIN, new Chip(200));
        softly.assertThat(player.getChip().getChip()).isEqualTo(300);
    }

    @Test
    public void ScoreTest() {
        if (21 > NumberManager.BLACKJACK_NUMBER) {
            log.debug("adfasdfas");
        }

    }
}