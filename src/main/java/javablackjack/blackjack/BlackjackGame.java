package javablackjack.blackjack;

import javablackjack.blackjack.domain.Card;
import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;


public class BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Player user;
    private Player dealer;
    private boolean userTurn = true;

    // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다. 의존관계 분리
    private CardDeck cardDeck = new CardDeck();

    // 플레이어에게 넘겨

    private GameResult result = GameResult.DEAULF;
    // todo 인스턴스 변수 어떻게 하면 줄일 수 있을까?


    public void initUser(Player player, Player dealer) {
        this.user = player;
        this.dealer = dealer;
    }

    //todo 줄이고싶다.
    public void startGame() {
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
    }

    public String checkBlackjack() {
        if (user.isBlackjack() && dealer.isBlackjack()) {
            result = GameResult.PUSH;
            userTurn = false;
            return result.getGameResult();
        }

        if (user.isBlackjack() && !dealer.isBlackjack()) {
            result = GameResult.USUER_WIN;
            userTurn = false;
            return result.getGameResult();
        }

        if (!user.isBlackjack() && dealer.isBlackjack()) {
            result = GameResult.DEALER_WIN;
            userTurn = false;
            return result.getGameResult();
        }

        System.out.println("딜러의 총합 : " + dealer.score());
        System.out.println("유저의 총합 : " + user.score());
        return result.getGameResult();
    }

    // todo : 유저가 모두 스탠드 할때 총합 출력용도 view 단으로 이동
    public void showCard() {
        System.out.println(user.getName());
        System.out.println(user.getCards());
        System.out.println("----------------");
        System.out.println(dealer.getName());
        System.out.println(dealer.getCards());
    }

    //todo view 단으로 이동, refactoring
    public void winner() {
        System.out.println();
        // 1.유저가 버스트가 안나거나 2.유저가 블랙잭이 아니거나 3.딜러가 버스트 안나거나 4.딜러가 블랙잭이 아닐때
        // 점수 비교
        if (!isUserTurnTrue() && result.equals(GameResult.DEAULF)) {
            if (user.compareScore(dealer)) {
                result = GameResult.USUER_WIN;
            }

            if (dealer.compareScore(user)) {
                result = GameResult.DEALER_WIN;
            }

            if (dealer.score() == user.score()) {
                result = GameResult.PUSH;
            }
        }


        System.out.println("딜러의 총합 : " + dealer.score());
        System.out.println("유저의 총합 : " + user.score());
    }

    //todo : 리팩토링 해야함
    public void dealerTurn() {
        if (result.equals(GameResult.DEAULF)) {
            while (dealer.score() < 17) {
                dealer.drawCard(cardDeck.drawCard());
                showCard();
            }
        }
        if (dealer.isBurst()) {
            result = GameResult.USUER_WIN;
        }
    }

    //todo : 리팩토링 해야함
    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());
            /////////
            if (user.isBurst()) {
                result = GameResult.DEALER_WIN;
                userTurn = false;
            }
            showCard();
        }
        if (choiceNumber == 2) {
            userTurn = false;
        }
    }

    public boolean isUserTurnTrue() {
        return userTurn;
    }

    public GameResult getResult() {
        return result;
    }
}
