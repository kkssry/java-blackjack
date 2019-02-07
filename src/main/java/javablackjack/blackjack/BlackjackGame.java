package javablackjack.blackjack;

import javablackjack.blackjack.domain.Card;
import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;


public class BlackjackGame {
    private Player user;
    private Player dealer = new Player("딜러");
    private CardDeck cardDeck = new CardDeck();
    private boolean userTurn = true;


    public void initUser(String userName) {
        this.user = new Player(userName);
    }

    public void startGame() {
        dealer.drawCard(cardDeck);
        user.drawCard(cardDeck);
        dealer.drawCard(cardDeck);
        user.drawCard(cardDeck);
    }

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

    public void winner() {
        System.out.println("우승자");
        if (user.score() > dealer.score()) {
            System.out.println(user.getName());
        } else {
            System.out.println(dealer.getName());
        }
    }


    public void UserChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck);
        }
        if (choiceNumber == 2) {
            userTurn = false;
        }
    }

    public boolean isUserTurnTrue() {
        return userTurn;
    }
}
