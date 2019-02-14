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
    private String result = "";
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
                return result = "push";
            }
            return result = "userWin";
        }
        if (dealer.isBlackjack()) {
            result = "dealerWin";
        }
        return result;
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

        if (this.result.equals("push")) {
            System.out.println("비겼습니다.");
        }

        if (result.equals("userWin")) {
            System.out.println("유저가 이겼습니다.");
        }

        if (result.equals("dealerWin")) {
            System.out.println("딜러가 이겼습니다.");
        }

        if (result.equals("")) {
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
                result = "dealerWin";
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

    public void push() {
        System.out.println("비겼습니다.");
    }
}
