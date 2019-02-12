package javablackjack.blackjack;

import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.io.Input;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        String userName = input.getString();
//// 게임 new GameController(blackjackGame, cardDeck);
        // (user)
        BlackjackGame blackjackGame = new BlackjackGame();
        CardDeck cardDeck = new CardDeck();

        blackjackGame.initUser(userName);
        blackjackGame.startGame();
        blackjackGame.showCard();

        while (blackjackGame.isUserTurnTrue()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());

        }

        // 히트 오어 스텐드 1 , 2
        // 1,2
        //todo 딜러 시크릿카드를 오픈시켜야함.
        blackjackGame.showCard();

        blackjackGame.winner();

    }




}
