package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;


public class CardTest extends BaseTest {
    @Test
    public void card_deck() {
        CardDeck cardDeck = new CardDeck();
        for (Card card : cardDeck.getCards()) {
            System.out.println(card);
        }
        softly.assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    public void card_num_vo_test() {
        Number num = Number.A;
        softly.assertThat(num.getNum()).isEqualTo(1);

        Number num1 = Number.K;
        softly.assertThat(num1.getNum()).isEqualTo(10);


    }

}