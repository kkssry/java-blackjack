package javablackjack.blackjack.domain;

import javablackjack.blackjack.BaseTest;
import org.junit.Test;

public class PlayerTest extends BaseTest {

    @Test
    public void player_have_money() {
        Player player = new Player("bb");
        //player.setMoney(3);
//        softly.assertThat(player.hasMoney()).isEqualTo(3);
    }

    @Test
    public void give_card_to_player() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("aa");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));

        //todo 렌덤값을 구하고 뽑는 메소드를 분리하라
        softly.assertThat(player.getCard(0)).isNotNull();
        //플레이어가 숫자를 꺼내는 메소드까지는 가질 필요 없을것 같다.
        softly.assertThat(player.getCard(0).getNum()).isEqualTo(11);
        softly.assertThat(player.getCard(1).getNum()).isEqualTo(7);

    }

    @Test
    public void player_burst_with_A() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));


        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.showScore()).isEqualTo(14);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A2() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));


        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.showScore()).isEqualTo(14);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_with_A3() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.CLOVER));
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));

        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.showScore()).isEqualTo(12);
        softly.assertThat(player.isBurst()).isFalse();
        // hit
        player.drawCard(new Card(Number.A,CardPattern.HEART));

        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.showScore()).isEqualTo(13);
        softly.assertThat(player.isBurst()).isFalse();

    }

    @Test
    public void player_burst_not_a() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.J,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SEVEN,CardPattern.CLOVER));
        player.drawCard(new Card(Number.SIX,CardPattern.CLOVER));


        softly.assertThat(player.isBurst()).isTrue();
        softly.assertThat(player.showScore()).isEqualTo(23);

    }

    @Test
    public void player_hava_elevenA() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));
        softly.assertThat(player.isElevenA()).isTrue();

    }

    @Test
    public void player_hava_oneA() {
        Player player = new Player("스컬");
        player.drawCard(new Card(Number.A,CardPattern.DIAMOND));
        player.setOneA();
        softly.assertThat(player.isElevenA()).isFalse();

    }
}