package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest extends BaseTest {

    @Test
    public void player_have_money() {
        Player player = new Player();
        player.setMoney(3);
        softly.assertThat(player.hasMoney()).isEqualTo(3);
    }

}