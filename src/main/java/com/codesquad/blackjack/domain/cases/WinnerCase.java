package com.codesquad.blackjack.domain.cases;

import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.player.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WinnerCase extends AbstractResultCases {
    @Override
    public List<Function<Pair, GameResult>> caseList() {
        List<Function<Pair, GameResult>> winner = new ArrayList<>();
        winner.add((pair) -> getGameResult(pair.isPush(), GameResult.PUSH));
        winner.add((pair) -> getGameResult(pair.isUserWin(), GameResult.USER_WIN));
        winner.add((pair) -> getGameResult(pair.isDealerWin(), GameResult.DEALER_WIN));
        return winner;
    }
}
