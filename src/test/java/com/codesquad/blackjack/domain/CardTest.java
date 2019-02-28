package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.BaseTest;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.CardDeck;
import com.codesquad.blackjack.domain.card.CardDeckFactory;
import com.codesquad.blackjack.domain.card.Number;
import org.junit.Test;


public class CardTest extends BaseTest {
    @Test
    public void card_deck() {
        CardDeck cardDeck = CardDeckFactory.create();
        for (Card card : cardDeck.getCards()) {
            System.out.println(card);
        }
        softly.assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    public void card_num_vo_test() {
        Number num = Number.A;
        softly.assertThat(num.getNum()).isEqualTo(11);

        Number num1 = Number.K;
        softly.assertThat(num1.getNum()).isEqualTo(10);

    }

    @Test
    public void card_string_test() {
        Number num = Number.A;
        softly.assertThat(num.name()).isEqualTo("A");
    }
}