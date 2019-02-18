package javablackjack.blackjack;

import javablackjack.blackjack.domain.BlackjackGame;
import javablackjack.blackjack.domain.Player;
import javablackjack.blackjack.io.Input;
import javablackjack.blackjack.io.OutputResult;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        BlackjackGame blackjackGame = new BlackjackGame();

        blackjackGame.initUser(new Player(input.getString()), new Player("dealer"));
        blackjackGame.startGame();

        OutputResult.showCard(blackjackGame.getPair());

        blackjackGame.checkBlackjack();
        while (blackjackGame.isUserTurn()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
            OutputResult.showCard(blackjackGame.getPair());
        }
        System.out.println("!@#!@#!@#!@#!@#1");
        while (blackjackGame.isDealerTurn()) {
            blackjackGame.dealerTurn();
            OutputResult.showCard(blackjackGame.getPair());
        }

        blackjackGame.winner();

        OutputResult.showWinner(blackjackGame);
        OutputResult.resultPrint(blackjackGame.getPair());
    }
}
