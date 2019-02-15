package javablackjack.blackjack;

import javablackjack.blackjack.domain.BlackjackGame;
import javablackjack.blackjack.domain.Player;
import javablackjack.blackjack.io.Input;
import javablackjack.blackjack.io.Output;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Input input = new Input();

        BlackjackGame blackjackGame = new BlackjackGame();

        blackjackGame.initUser(new Player(input.getString()), new Player("dealer"));

        blackjackGame.startGame();
        blackjackGame.showCard();
        blackjackGame.checkBlackjack();

        while (blackjackGame.isUserTurn()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
        }

        blackjackGame.dealerTurn();
        blackjackGame.winner();

        Output.resultPrint(blackjackGame.getResult());
    }
}
