package javablackjack.blackjack;

import javablackjack.blackjack.domain.*;
import javablackjack.blackjack.io.Input;
import javablackjack.blackjack.io.OutputResult;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        GameResult result;

        Input input = new Input();


        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());

        blackjackGame.initUser(new Player(input.getString()), new Player("dealer"));

        OutputResult.bettingChip();
        Chip bettingChip = new Chip(input.getint());
        blackjackGame.startGame(bettingChip);

        OutputResult.showCard(blackjackGame.getPair());

        result = blackjackGame.checkBlackjack();
        blackjackGame.playerTurnFinish(result);


        //유저턴
        while (blackjackGame.isUserTurn()) {
            result = blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
            blackjackGame.playerTurnFinish(result);

            OutputResult.showCard(blackjackGame.getPair());
        }

        // 딜러턴
        while (blackjackGame.isDealerTurn()) {
            result = blackjackGame.dealerTurn();
            blackjackGame.playerTurnFinish(result);

            OutputResult.showCard(blackjackGame.getPair());
        }

        if (result.isDefault()) {
            result = blackjackGame.winner();
        }

        blackjackGame.manageChip(result, bettingChip);
        OutputResult.showWinner(blackjackGame);
        OutputResult.resultPrint(result);
    }
}
