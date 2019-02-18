package javablackjack.blackjack.io;

import javablackjack.blackjack.domain.BlackjackGame;
import javablackjack.blackjack.domain.Pair;
import javablackjack.blackjack.domain.ResultCases;

public class OutputResult {
    public static void showCard(Pair pair) {
        System.out.println("----------------");
        System.out.println(pair.getUser().getName());
        System.out.println(pair.getUser().getCards());
        System.out.println(pair.getDealer().getName());
        System.out.println(pair.getDealer().getCards());
        System.out.println("----------------");
    }

    public static void showWinner(BlackjackGame blackjackGame) {
        System.out.println();
        System.out.println("딜러의 총합 : " + blackjackGame.getPair().getDealer().score());
        System.out.println("유저의 총합 : " + blackjackGame.getPair().getUser().score());
    }

    public static void resultPrint(Pair pair) {
        System.out.println(pair.getResult());
    }
}
