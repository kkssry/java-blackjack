package javablackjack.blackjack.domain.cases;

import javablackjack.blackjack.domain.GameResult;


public abstract class AbstractResultCases implements ResultCases {

    static GameResult getGameResult(boolean case1, boolean case2, GameResult gameResult) {
        if (case1 && case2) {
            return gameResult;
        }
        return GameResult.DEFAULT;
    }

    static GameResult getGameResult1(boolean case1, GameResult gameResult) {
        if (case1) {
            return gameResult;
        }
        return GameResult.DEFAULT;
    }
}
