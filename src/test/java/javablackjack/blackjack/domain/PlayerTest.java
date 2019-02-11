package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;

public class PlayerTest extends BaseTest {

    @Test
    public void player_have_money() {
        Player player = new Player("bb");
        player.setMoney(3);
        softly.assertThat(player.hasMoney()).isEqualTo(3);
    }

    @Test
    public void give_card_to_player() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("aa");
        player.drawCard(cardDeck.drawCard());
        player.drawCard(cardDeck.drawCard());

        softly.assertThat(player.getCards().get(0)).isNotNull();
        System.out.println(player.getCards().get(0));
        System.out.println(player.getCards().get(1));
        softly.assertThat(cardDeck.getCards().size()).isEqualTo(50);
    }
}