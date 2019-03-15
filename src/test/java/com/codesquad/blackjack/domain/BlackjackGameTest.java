package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.BaseTest;
import com.codesquad.blackjack.domain.card.*;
import com.codesquad.blackjack.domain.card.Number;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGameTest extends BaseTest {
    private static final Logger log = getLogger(BlackjackGameTest.class);
    private Card card_A;

    @Before
    public void setup() {
        card_A = new Card(Number.A, CardPattern.CLOVER);
    }

    @Test
    public void 딜러셋업() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.J, CardPattern.CLOVER));
        list.add(new Card(Number.K, CardPattern.CLOVER));
        list.add(new Card(Number.SEVEN, CardPattern.CLOVER));
        list.add(new Card(Number.Q, CardPattern.CLOVER));
        list.add(new Card(Number.TWO, CardPattern.CLOVER));

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(list));
        //user
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");
        blackjackGame.initUser(user, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();

        blackjackGame.userChoiceHitOrStand(2);
        blackjackGame.dealerTurn();
        log.debug("유저숫자 합 : {}", user.score());
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isTrue();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
    }

    @Test
    public void user_win() {
        // 유저는 20 딜러는 19로 패배
        BlackjackGame blackjackGame1 = new BlackjackGame(CardDeckFactory.create());
        User user1 = new User("skull");
        Dealer dealer1 = new Dealer("dealer");

        user1.drawCard(card_A);
        user1.drawCard(new Card(Number.NINE, CardPattern.SPADE));
        log.debug("플레이어 카드 {}", user1.getCards());
        dealer1.drawCard(card_A);
        dealer1.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));
        log.debug("e딜ㄹ러 카드 {}", dealer1.getCards());

        blackjackGame1.initUser(user1, dealer1);

        blackjackGame1.winner();
        log.debug("유저점수 : {}", user1.score());
        softly.assertThat(user1.score()).isEqualTo(20);
        softly.assertThat(dealer1.score()).isEqualTo(19);
    }

    @Test
    public void user_blackjack_win() {
        //유저가 21나와서 승리
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);

        blackjackGame.winner();
        softly.assertThat(user.score()).isEqualTo(21);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }


    //3유저 패배
    @Test
    public void dealer_win() {
        //유저는 17 딜러는 19 승리
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);

        blackjackGame.winner();

        softly.assertThat(user.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }

    @Test
    public void dealer_blackjack_win() {
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.K, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);

        GameResult result = blackjackGame.checkBlackjack();
        blackjackGame.playerTurnFinish(result);

        softly.assertThat(user.isTurn()).isFalse();
        softly.assertThat(user.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(21);
    }

    @Test
    public void game_push() {
        //둘다 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.Q, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);

        blackjackGame.checkBlackjack();
        blackjackGame.winner();


        softly.assertThat(user.score()).isEqualTo(21);
        softly.assertThat(dealer.score()).isEqualTo(21);
    }

    @Test
    public void checkblackjack() {
        //둘다 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.Q, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.PUSH);

    }

    @Test
    public void checkblackjack_user() {
        //유저 블랙잭
        BlackjackGame blackjackGame2 = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(new Card(Number.A, CardPattern.HEART));
        dealer.drawCard(new Card(Number.A, CardPattern.SPADE));

        blackjackGame2.initUser(user, dealer);
        log.debug("ㄻㄴㄻㄴ{}", user.score());

        softly.assertThat(blackjackGame2.checkBlackjack()).isEqualTo(GameResult.BLACKJACK_USER_WIN);

    }

    @Test
    public void checkblackjack_dealer() {
        //딜러 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");

        user.drawCard(card_A);
        user.drawCard(new Card(Number.FOUR, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.K, CardPattern.SPADE));

        blackjackGame.initUser(user, dealer);
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.BLACKJACK_DEALER_WIN);

    }


    @Test
    public void user_win_20() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.J, CardPattern.CLOVER));
        list.add(new Card(Number.K, CardPattern.CLOVER));
        list.add(new Card(Number.FIVE, CardPattern.CLOVER));
        list.add(new Card(Number.Q, CardPattern.CLOVER));
        list.add(new Card(Number.TWO, CardPattern.CLOVER));

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(list));
        //user
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");
        blackjackGame.initUser(user, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();

        blackjackGame.userChoiceHitOrStand(2);
        blackjackGame.dealerTurn();
        log.debug("유저 카드 : {}", user.getCards());
        log.debug("유저숫자 합 : {}", user.score());
        log.debug("딜러 카드 : {}", dealer.getCards());
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isTrue();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
        softly.assertThat(blackjackGame.winner()).isEqualTo(GameResult.USER_WIN);
    }

    @Test
    public void dealer_win_20() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.K, CardPattern.CLOVER));
        list.add(new Card(Number.J, CardPattern.CLOVER));
        list.add(new Card(Number.Q, CardPattern.CLOVER));
        list.add(new Card(Number.FIVE, CardPattern.CLOVER));
        list.add(new Card(Number.TWO, CardPattern.CLOVER));
        list.add(new Card(Number.THREE, CardPattern.CLOVER));

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(list));
        //user
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");
        blackjackGame.initUser(user, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();

        blackjackGame.userChoiceHitOrStand(1);
        blackjackGame.userChoiceHitOrStand(2);
        blackjackGame.dealerTurn();
        log.debug("유저 카드 : {}", user.getCards());
        log.debug("유저숫자 합 : {}", user.score());
        log.debug("딜러 카드 : {}", dealer.getCards());
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isTrue();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
        softly.assertThat(blackjackGame.winner()).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    public void dealer_win_21() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.K, CardPattern.CLOVER));
        list.add(new Card(Number.J, CardPattern.CLOVER));
        list.add(new Card(Number.A, CardPattern.CLOVER));
        list.add(new Card(Number.FIVE, CardPattern.CLOVER));

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(list));
        //user
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");
        blackjackGame.initUser(user, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();

        log.debug("유저 카드 : {}", user.getCards());
        log.debug("유저숫자 합 : {}", user.score());
        log.debug("딜러 카드 : {}", dealer.getCards());
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isTrue();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.BLACKJACK_DEALER_WIN);
    }

    @Test
    public void user_win_21() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.K, CardPattern.CLOVER));
        list.add(new Card(Number.J, CardPattern.CLOVER));
        list.add(new Card(Number.FIVE, CardPattern.CLOVER));
        list.add(new Card(Number.A, CardPattern.CLOVER));

        BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(list));
        //user
        User user = new User("skull");
        Dealer dealer = new Dealer("dealer");
        blackjackGame.initUser(user, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();

        log.debug("유저 카드 : {}", user.getCards());
        log.debug("유저숫자 합 : {}", user.score());
        log.debug("딜러 카드 : {}", dealer.getCards());
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isFalse();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.BLACKJACK_USER_WIN);
    }
}