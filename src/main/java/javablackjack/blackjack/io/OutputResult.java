package javablackjack.blackjack.io;

import javablackjack.blackjack.domain.BlackjackGame;
import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputResult {
    private static final Logger log = LogManager.getLogger(OutputResult.class);

    public static void showCard(Pair pair) {
        System.out.println("----------------");
        System.out.println(pair.getUser().getName());
        System.out.println(pair.getUser().getCards());
        System.out.println(pair.getDealer().getName());
        System.out.println(pair.getDealer().getCards());
        System.out.println("----------------");

        log.debug("딜러의 총합 : {}", pair.getDealer().score());
        log.debug("유저의 총합 : {}", pair.getUser().score());
    }

    public static void showWinner(BlackjackGame blackjackGame) {
        System.out.println();
        System.out.println("딜러의 총합 : " + blackjackGame.getPair().getDealer().score());
        System.out.println("유저의 총합 : " + blackjackGame.getPair().getUser().score());
    }

    public static void resultPrint(GameResult result) {
        System.out.println(result.getGameResult());
    }
}
