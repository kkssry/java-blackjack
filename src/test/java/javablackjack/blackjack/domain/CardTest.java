package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;


public class CardTest extends BaseTest {
    @Test
    public void card_deck() {
        CardDeck cardDeck = new CardDeck();
        softly.assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    public void card_num_vo_test() {
        EnumCard num = EnumCard.A;
        softly.assertThat(num.getNum()).isEqualTo(1);

    }

}