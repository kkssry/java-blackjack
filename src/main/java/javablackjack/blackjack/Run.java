package javablackjack.blackjack;

import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;
import javablackjack.blackjack.io.Input;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {


        Input input = new Input();
        String userName = input.getString();


//// 게임 new GameController(blackjackGame, cardDeck);
        // (user)


        BlackjackGame blackjackGame = new BlackjackGame();

        Player player = new Player(userName);
        Player dealer = new Player("dealer");

        //Player, dealer 입장.
        blackjackGame.initUser(player, dealer);

        // 1. 2장씩 카드를 받고, 2. 받은 카드를 보여주고 3. 블랙잭 여부 각각확인.
        blackjackGame.startGame();

        while (blackjackGame.isUserTurnTrue()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
        }

        // 히트 오어 스텐드 1, 2
        // 1,2
        //todo 딜러 시크릿카드를 오픈시켜야함.
//        blackjackGame.showCard();

        blackjackGame.winner();


    }
}
