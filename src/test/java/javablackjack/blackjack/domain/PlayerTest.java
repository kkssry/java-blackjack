package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import javablackjack.blackjack.util.NumberManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class PlayerTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(PlayerTest.class);

    @Test
    public void player_have_money() {
        Player player = new Player("bb");;
        softly.assertThat( player.getChip().getChip()).isEqualTo(500);
    }

    @Test
    public void win_chip() {
        Player player = new Player("peter");
        Chip chip = new Chip(200);

        player.betting(chip);
        softly.assertThat(player.getChip().getChip()).isEqualTo(300);
        player.winningChip(chip);

        softly.assertThat(player.getChip().getChip()).isEqualTo(700);
    }

    @Test
    public void lose_chip() {
        Player player = new Player("peter");
        Chip chip = new Chip(200);
        player.betting(chip);
        softly.assertThat(player.getChip().getChip()).isEqualTo(300);
    }

    @Test
    public void give_card_to_player() {
        Player player = new Player("aa");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));

        softly.assertThat(player.getCard(0)).isNotNull();
        softly.assertThat(player.getCard(0).getNum()).isEqualTo(11);
        softly.assertThat(player.getCard(1).getNum()).isEqualTo(7);

    }

    @Test
    public void player_burst_with_A() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));
        log.debug("카드 점수 : {}",new Card(Number.A,CardPattern.CLOVER).getNum());

        log.debug("플레이어 점수 : {}",player.score());

        softly.assertThat(player.score()).isEqualTo(14);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A2() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));

        softly.assertThat(player.score()).isEqualTo(14);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A3() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));

        softly.assertThat(player.score()).isEqualTo(12);
        softly.assertThat(player.isBurst()).isFalse();
        // hit
        player.drawCard(new Card(Number.A,CardPattern.HEART));


        softly.assertThat(player.score()).isEqualTo(13);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_not_a() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.J,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));


        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.score()).isEqualTo(23);

    }

    @Test
    public void player_hava_elevenA() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));
        softly.assertThat(player.isContainElevenA()).isTrue();

    }

    @Test
    public void player_hava_oneA() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.Q,CardPattern.SPADE));
        player.drawCard(new Card(Number.K,CardPattern.SPADE));
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));
        player.setOneA();
        softly.assertThat(player.isContainElevenA()).isFalse();
        softly.assertThat(player.isBlackjack()).isTrue();
    }
}