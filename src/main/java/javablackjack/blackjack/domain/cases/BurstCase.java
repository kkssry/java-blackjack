package javablackjack.blackjack.domain.cases;

import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BurstCase extends AbstractResultCases {
    @Override
    public List<Function<Pair, GameResult>> caseList() {
        List<Function<Pair, GameResult>> list = new ArrayList<>();
        list.add((pair) -> getGameResult(pair.getUser().isBurst(), !pair.getDealer().isBurst(), GameResult.DEALER_WIN));
        list.add((pair) -> getGameResult(!pair.getUser().isBurst(), pair.getDealer().isBurst(), GameResult.USER_WIN));
        return list;
    }
}
