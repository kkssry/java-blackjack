package javablackjack.blackjack;

import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;
import javablackjack.blackjack.io.Input;
import javablackjack.blackjack.io.Output;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {


        Input input = new Input();
        String userName = input.getString();


//// 게임 new GameController(blackjackGame, cardDeck);
        // (user)


        BlackjackGame blackjackGame = new BlackjackGame();

        Player player = new Player(userName);
        //Player, dealer 입장.
        blackjackGame.initUser(player, new Player("dealer"));

        // 1. 2장씩 카드를 받고
        blackjackGame.startGame();
        blackjackGame.showCard();
        //3. 블랙잭 확인  (유저가 블랙잭인지 , 둘다 블랙잭인지, 딜러만 블랙잭인지 확인) 승패가 갈리면 유저턴= false
        //blackjackGame.winner();
        blackjackGame.checkBlackjack();
        //3.  위너 한번 확인

        // 히트 올 스탠드
        while (player.isTurn()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
        }


        blackjackGame.dealerTurn();
        // 위너 확인
        blackjackGame.winner();


        Output.resultPrint(blackjackGame.getResult());

    }
}
