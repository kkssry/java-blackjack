package com.codesquad.blackjack.domain.cases;

import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.player.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BlackjackCase extends AbstractResultCases {
    @Override
    public List<Function<Pair, GameResult>> caseList() {
        List<Function<Pair, GameResult>> blackjack = new ArrayList<>();

        blackjack.add((pair) -> getGameResult(pair.isPush(), GameResult.PUSH));  //default
        blackjack.add((pair) -> getGameResult(pair.isUserWin(), GameResult.BLACKJACK_USER_WIN));  //default
        blackjack.add((pair) -> getGameResult(pair.isDealerWin(), GameResult.BLACKJACK_DEALER_WIN));  //default

        return blackjack;
    }
}
