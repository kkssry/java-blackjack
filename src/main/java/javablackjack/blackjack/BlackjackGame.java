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

    // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다. 의존관계 분리
    private CardDeck cardDeck = new CardDeck();

    private boolean userTurn = true;
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
        showCard();
        checkBlackjack();
    }

    public String checkBlackjack() {
        if (user.isBlackjack()) {
            userTurn = false;
            if (dealer.isBlackjack()) {
                result = GameResult.PUSH;
                return result.getGameResult();
            }
            result = GameResult.USUER_WIN;
            return result.getGameResult();
        }
        if (dealer.isBlackjack()) {
            result = GameResult.DEALER_WIN;
            return result.getGameResult();
        }
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
        System.out.println(result.getGameResult());

        if (result.equals(GameResult.DEAULF)) {
            if (user.compareScore(dealer)) {
                System.out.println("우승자는 " + user.getName());
            } else {
                System.out.println("우승자는 " + dealer.getName());
            }
        }

        System.out.println("딜러의 총합 : " + dealer.score());
        System.out.println("유저의 총합 : " + user.score());
    }


    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());
            user.setOneA();
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

    public void dealerTurn() {
        while (dealerScore() <= 16) {
            dealer.drawCard(cardDeck.drawCard());
            dealer.setOneA();
        }
    }

    public boolean isDealerTurnFinish() {
        return dealer.score() > 16;
    }

    public int dealerScore() {
        return dealer.score();
    }

    public boolean isDealerBurst() {
        return dealer.isBurst();
    }

}
