package javablackjack.blackjack.domain.cases;

import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.Pair;

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
