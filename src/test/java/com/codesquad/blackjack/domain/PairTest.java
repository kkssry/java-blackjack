package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.BaseTest;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.CardDeck;
import com.codesquad.blackjack.domain.card.CardPattern;
import com.codesquad.blackjack.domain.card.Number;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.Pair;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.util.NumberManager;
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

        User user = new User("peter");
        user.drawCard(new Card(Number.FIVE, CardPattern.DIAMOND));
        user.drawCard(new Card(Number.SIX, CardPattern.DIAMOND));

        Dealer dealer = new Dealer("dealer");

        blackjackGame.initUser(user, dealer);

        aCard.add(new Card(Number.K, CardPattern.CLOVER));
        blackjackGame.getPair().userChoiceHitOrStand(1, new CardDeck(aCard));
        log.debug("userScore : {}", user.score());

        softly.assertThat(dealer.isTurn()).isTrue();
        softly.assertThat(user.isTurn()).isFalse();
    }

    @Test
    public void player_win_increase_Chip() {
        User user = new User("peter");
        Dealer dealer = new Dealer("dealer");

        Pair pair = new Pair(user, dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.USER_WIN, new Chip(200));
        softly.assertThat(user.getChip().getChip()).isEqualTo(700);
    }

    @Test
    public void player_blackjack_increase_Chip() {
        User user = new User("peter");
        Dealer dealer = new Dealer("dealer");

        Pair pair = new Pair(user, dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.BLACKJACK_USER_WIN, new Chip(200));
        softly.assertThat(user.getChip().getChip()).isEqualTo(800);
    }

    @Test
    public void dealer_blakcjack_win_increase_Chip() {
        User user = new User("peter");
        Dealer dealer = new Dealer("dealer");

        Pair pair = new Pair(user, dealer);
        pair.bettingChip(new Chip(200));
        pair.increaseChip(GameResult.BLACKJACK_DEALER_WIN, new Chip(200));
        softly.assertThat(user.getChip().getChip()).isEqualTo(300);
    }

    @Test
    public void ScoreTest() {
        if (21 > NumberManager.BLACKJACK_NUMBER) {
            log.debug("adfasdfas");
        }

    }
}