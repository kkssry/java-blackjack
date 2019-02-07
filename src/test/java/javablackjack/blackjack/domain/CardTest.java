package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;


public class CardTest extends BaseTest {
    @Test
    public void card_deck() {
        CardDeck cardDeck = new CardDeck();
        Card card = new Card();
        softly.assertThat(card.hasNumber()).isEqualTo(3);
        softly.assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }
}