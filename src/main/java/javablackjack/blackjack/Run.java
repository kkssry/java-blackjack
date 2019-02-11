package javablackjack.blackjack;

import javablackjack.blackjack.io.Input;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        String userName = input.getString();

        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.initUser(userName);
        blackjackGame.startGame();
        blackjackGame.showCard();

        while (blackjackGame.isUserTurnTrue()) {
            blackjackGame.UserChoiceHitOrStand(input.choiceHitOrstand());

        }

        // 히트 오어 스텐드 1 , 2
        // 1,2
        blackjackGame.showCard();

        blackjackGame.winner();

    }




}
