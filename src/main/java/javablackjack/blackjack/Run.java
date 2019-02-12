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
        CardDeck cardDeck = new CardDeck();

        Player player = new Player(userName);
        Player dealer = new Player("dealer");

        blackjackGame.initUser(player, dealer);
        blackjackGame.startGame();

        blackjackGame.showCard();
        if (player.isBlackjack()) {
            if (dealer.isBlackjack()) {
                blackjackGame.draw();
            }
            blackjackGame.winner();
        }
        //딜러가 블랙잭인지 확인
        // 딜러가 블랙잭이면 플래이어가 블랙잭인지 확인 결과 리턴
        // 플레이어가 블랙잭이면 턴바로 종료? 플레이어 턴 종료 리턴

        while (blackjackGame.isUserTurnTrue()) {
            blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());

        }

        // 히트 오어 스텐드 1, 2
        // 1,2
        //todo 딜러 시크릿카드를 오픈시켜야함.
        blackjackGame.showCard();

        blackjackGame.winner();


    }


}
