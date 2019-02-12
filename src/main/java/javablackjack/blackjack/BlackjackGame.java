package javablackjack.blackjack;

import javablackjack.blackjack.domain.Card;
import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;


public class BlackjackGame {
    private Player user;
    private Player dealer;

    // 이걸 밖으로 빼면 렌덤 태스트가 가능하다. 의존관계 분리
    private CardDeck cardDeck = new CardDeck();
    private boolean userTurn = true;
// 인스턴스변수 많아지면 뽑아라?


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

    // todo : 유저가 모두 스탠드 할때 총합 출력용도 view 단으로 이동
    public void showCard() {
        System.out.println(user.getName());
        for (Card card : user.getCards()) {
            System.out.println(card.getStringNum());
        }
        System.out.println("----------------");
        System.out.println(dealer.getName());
        System.out.println(dealer.getCards().get(0).getStringNum());
        System.out.println("시크릿 카드");
    }

    //todo view 단으로 이동
    public void winner() {
        System.out.println("우승자");
        if (user.compareScore(dealer)) {
            System.out.println(user.getName());
            System.out.println("유저의 총합 : " + user.score());
            System.out.println("딜러의 총합 : " + dealer.score());
        } else {
            System.out.println(dealer.getName());
            System.out.println("딜러의 총합 : " + dealer.score());
            System.out.println("유저의 총합 : " + user.score());
        }
    }


    // 카드를 파라미터에 넣어 외부에서 받아라.
    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());
            if (user.isBurst()) {
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

    public void draw() {
        System.out.println("비겼습니다.");
    }
}
