package javablackjack.blackjack;

import javablackjack.blackjack.domain.Card;
import javablackjack.blackjack.domain.CardPattern;
import javablackjack.blackjack.domain.Number;
import javablackjack.blackjack.domain.Player;
import org.junit.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BlackjackGameTest extends BaseTest {
    private static final Logger log = getLogger(BlackjackGameTest.class);

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
        log.debug("딜러숫자 합 : {}", blackjackGame.dealerScore());
        softly.assertThat(blackjackGame.isDealerTurnFinish()).isTrue();
        log.debug("딜러버스트여부 : {}", blackjackGame.isDealerBurst());
    }

    //game
    // 유저 승리

    @Test
    public void user_win() {
        // 유저는 20 딜러는 19로 패배
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(Card.ELEVEN_A);
        player.drawCard(new Card(Number.NINE, CardPattern.SPADE));

        dealer.drawCard(Card.ELEVEN_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.winner();

        softly.assertThat(player.score()).isEqualTo(20);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }


    //2유저 블랙잭

    @Test
    public void user_blackjack_win() {
        //유저가 21나와서 승리
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(Card.ELEVEN_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(Card.ELEVEN_A);
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

        player.drawCard(Card.ELEVEN_A);
        player.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(Card.ELEVEN_A);
        dealer.drawCard(new Card(Number.EIGHT, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);

        blackjackGame.winner();

        softly.assertThat(player.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(19);
    }

    //4 딜러 블랙잭 -> 딜러 바로 승리!
    @Test
    public void dealer_blackjack_win() {
        //딜러 블랙잭 -> 딜러 바로 승리!
        BlackjackGame blackjackGame = new BlackjackGame();
        Player player = new Player("skull");
        Player dealer = new Player("dealer");

        player.drawCard(Card.ELEVEN_A);
        player.drawCard(new Card(Number.SIX, CardPattern.SPADE));

        dealer.drawCard(Card.ELEVEN_A);
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

        player.drawCard(Card.ELEVEN_A);
        player.drawCard(new Card(Number.K, CardPattern.SPADE));

        dealer.drawCard(Card.ELEVEN_A);
        dealer.drawCard(new Card(Number.Q, CardPattern.SPADE));

        blackjackGame.initUser(player, dealer);


        blackjackGame.winner();

        softly.assertThat(player.score()).isEqualTo(17);
        softly.assertThat(dealer.score()).isEqualTo(21);
    }
}