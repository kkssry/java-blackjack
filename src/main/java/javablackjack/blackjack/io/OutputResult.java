package javablackjack.blackjack.io;

import javablackjack.blackjack.domain.BlackjackGame;
import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.card.Card;
import javablackjack.blackjack.domain.player.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OutputResult {
    private static final Logger log = LogManager.getLogger(OutputResult.class);

    public static void showCard(Pair pair) {
        System.out.println("----------------");
        System.out.println(pair.getUser().getName());
        System.out.println(pair.getUser().getCards());
        System.out.println(pair.getDealer().getName());
        System.out.println(pair.getDealer().getCards().get(0));
        System.out.println("----------------");

        log.debug("딜러의 총합 : {}", pair.getDealer().score());
        log.debug("유저의 총합 : {}", pair.getUser().score());
    }

    public static void showWinner(BlackjackGame blackjackGame) {
        System.out.println();
        System.out.println("딜러의 총합 : " + blackjackGame.getPair().getDealer().score());
        System.out.println("유저의 총합 : " + blackjackGame.getPair().getUser().score());
        System.out.println("보유 칩 : " + blackjackGame.getPair().getUser().getChip().getChip());
    }

    public static void bettingChip() {
        System.out.println("배팅할 칩을 입력하세요.");
    }

    public static void resultPrint(GameResult result) {
        System.out.println(result.getGameResult());
    }

   /* public static void isDealerHaveJQKA(Pair pair) {
       List<Card> aaa =  pair.getDealer().getCards();
       if (aaa.get(0).equals()) {
           엑션
       }
       ㄴㄴㄴ;
    }*/
}
