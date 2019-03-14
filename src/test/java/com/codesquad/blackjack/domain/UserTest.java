package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.BaseTest;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.CardPattern;
import com.codesquad.blackjack.domain.card.Number;
import com.codesquad.blackjack.domain.player.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class UserTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(UserTest.class);

    @Test
    public void player_have_money() {
        User user = new User("bb");
        ;
        softly.assertThat(user.getChip().getChip()).isEqualTo(500);
    }

    @Test
    public void win_chip() {
        User user = new User("peter");
        Chip chip = new Chip(200);

        user.betting(chip);
        softly.assertThat(user.getChip().getChip()).isEqualTo(300);
        user.winningChip(chip);

        softly.assertThat(user.getChip().getChip()).isEqualTo(700);
    }

    @Test
    public void lose_chip() {
        User user = new User("peter");
        Chip chip = new Chip(200);
        user.betting(chip);
        softly.assertThat(user.getChip().getChip()).isEqualTo(300);
    }

    @Test
    public void give_card_to_player() {
        User user = new User("aa");
        user.drawCard(new Card(Number.A, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SEVEN, CardPattern.CLOVER));

        softly.assertThat(user.getCards().get(0)).isNotNull();
        softly.assertThat(user.getCards().get(0).bringNum()).isEqualTo(11);
        softly.assertThat(user.getCards().get(1).bringNum()).isEqualTo(7);

    }

    @Test
    public void player_burst_with_A() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.A, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SEVEN, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SIX, CardPattern.CLOVER));
        log.debug("카드 점수 : {}", new Card(Number.A, CardPattern.CLOVER).bringNum());

        log.debug("플레이어 점수 : {}", user.score());

        softly.assertThat(user.score()).isEqualTo(14);
        softly.assertThat(user.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A2() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.A, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SEVEN, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SIX, CardPattern.CLOVER));

        softly.assertThat(user.score()).isEqualTo(14);
        softly.assertThat(user.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A3() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.A, CardPattern.CLOVER));
        user.drawCard(new Card(Number.A, CardPattern.DIAMOND));

        softly.assertThat(user.score()).isEqualTo(12);
        softly.assertThat(user.isBurst()).isFalse();
        // hit
        user.drawCard(new Card(Number.A, CardPattern.HEART));


        softly.assertThat(user.score()).isEqualTo(13);
        softly.assertThat(user.isBurst()).isFalse();

    }

    @Test
    public void player_burst_not_a() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.J, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SEVEN, CardPattern.CLOVER));
        user.drawCard(new Card(Number.SIX, CardPattern.CLOVER));


        softly.assertThat(user.isBurst()).isTrue();
        softly.assertThat(user.score()).isEqualTo(23);

    }

    @Test
    public void player_hava_elevenA() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.A, CardPattern.DIAMOND));
        softly.assertThat(user.isContainElevenA()).isTrue();

    }

    @Test
    public void player_hava_oneA() {
        User user = new User("스컬");
        user.drawCard(new Card(Number.Q, CardPattern.SPADE));
        user.drawCard(new Card(Number.K, CardPattern.SPADE));
        user.drawCard(new Card(Number.A, CardPattern.DIAMOND));
        user.setOneA();
        softly.assertThat(user.isContainElevenA()).isFalse();
        softly.assertThat(user.isBlackjack()).isTrue();
    }
}