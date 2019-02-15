package javablackjack.blackjack;

import javablackjack.blackjack.domain.Card;
import javablackjack.blackjack.domain.CardPattern;
import javablackjack.blackjack.domain.Number;
import javablackjack.blackjack.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGameTest extends BaseTest {
    private static final Logger log = getLogger(BlackjackGameTest.class);
    private Card card_A;

    @Before
    public void setup() {
        card_A = new Card(Number.A,CardPattern.CLOVER);
    }

    @Test
    public void 딜러셋업() {
        BlackjackGame blackjackGame = new BlackjackGame();
        //player
        Player player = new Player("skull");
        Player dealer = new Player("dealer");
        blackjackGame.initUser(player, dealer);

        //카드를 블랙젝 게임에게 전달
        blackjackGame.startGame();
        blackjackGame.userChoiceHitOrStand(2);
        blackjackGame.dealerTurn();
        log.debug("딜러숫자 합 : {}", dealer.score());
        softly.assertThat(dealer.score() > 16).isTrue();
        log.debug("딜러버스트여부 : {}", dealer.isBurst());
    }

    @Test
    public void user_win() {
        // 유저는 20 딜러는 19로 패배
        BlackjackGame blackjackGame1 = new BlackjackGame();
        Player player1 = new Player("skull");
        Player dealer1 = new Player("dealer");

        player1.drawCard(card_A);
        player1.drawCard(new Card(Number.NINE, CardPattern.SPADE));
        log.debug("플레이어 카드 {}", player1.getCards());
        dealer1.drawCard(card_A);
        dealer1.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));
        log.debug("e딜ㄹ러 카드 {}", dealer1.getCards());

        blackjackGame1.initUser(player1, dealer1);

        blackjackGame1.winner();
        log.debug("유저점수 : {}", player1.score());
        softly.assertThat(player1.score()).isEqualTo(20);
        softly.assertThat(dealer1.score()).isEqualTo(19);
    }

    @Test
    public void user_blackjack_win() {
        //유저가 21나와서 승리
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.winner();
        softly.assertThat(player.score()).isEqualTo(21);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }


    //3유저 패배
    @Test
    public void dealer_win() {
        //유저는 17 딜러는 19 승리
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.winner();

        softly.assertThat(player.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }

    @Test
    public void dealer_blackjack_win() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.K, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.winner();

        softly.assertThat(player.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(21);
    }

    @Test
    public void game_push() {
        //둘다 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.Q, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.checkBlackjack();
        blackjackGame.winner();


        softly.assertThat(player.score()).isEqualTo(21);
        softly.assertThat(dealer.score()).isEqualTo(21);
    }

    @Test
    public void checkblackjack() {
        //둘다 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.Q, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.PUSH.getGameResult());

    }

    @Test
    public void checkblackjack_user() {
        //유저 블랙잭
        BlackjackGame blackjackGame2 = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(new Card(Number.A, CardPattern.HEART));
        dealer.drawCard(new Card(Number.A, CardPattern.SPADE));

        blackjackGame2.initUser(player, dealer);
        log.debug("ㄻㄴㄻㄴ{}",player.score());

        softly.assertThat(blackjackGame2.checkBlackjack()).isEqualTo(GameResult.USUER_WIN.getGameResult());

    }

    @Test
    public void checkblackjack_dealer() {
        //딜러 블랙잭
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(card_A);
        player.drawCard(new Card(Number.FOUR, CardPattern.SPADE));

        dealer.drawCard(card_A);
        dealer.drawCard(new Card(Number.K, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);
        softly.assertThat(blackjackGame.checkBlackjack()).isEqualTo(GameResult.DEALER_WIN.getGameResult());

    }


}