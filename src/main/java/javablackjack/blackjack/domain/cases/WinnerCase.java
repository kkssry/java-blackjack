package javablackjack.blackjack.domain.cases;

import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WinnerCase extends AbstractResultCases {
    @Override
    public List<Function<Pair, GameResult>> caseList() {
        List<Function<Pair, GameResult>> winner = new ArrayList<>();
//        winner.add((pair) -> getGameResult(!pair.getUser().isTurn(), pair.getUser().isEqualScore(pair.getDealer()), GameResult.PUSH));
        winner.add((pair) -> getGameResult1(pair.push(), GameResult.PUSH));
        winner.add((pair) -> getGameResult(!pair.getUser().isTurn(), pair.getUser().compareScore(pair.getDealer()), GameResult.USER_WIN));
        winner.add((pair) -> getGameResult(!pair.getUser().isTurn(), pair.getDealer().compareScore(pair.getUser()), GameResult.DEALER_WIN));
        return winner;
    }
}
