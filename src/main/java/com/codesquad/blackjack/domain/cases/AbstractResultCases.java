package com.codesquad.blackjack.domain.cases;

import com.codesquad.blackjack.domain.GameResult;


public abstract class AbstractResultCases implements ResultCases {

    static GameResult getGameResult(boolean case1, GameResult gameResult) {
        if (case1) {
            return gameResult;
        }
        return GameResult.DEFAULT;
    }
}
