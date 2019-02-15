package javablackjack.blackjack.io;

import javablackjack.blackjack.domain.GameResult;

public class Output {

    public static void resultPrint(GameResult gameResult) {
        System.out.println(gameResult.getGameResult());
    }
}
