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
        blackjack.add((pair) -> getGameResult(pair.getUser().isBlackjack(), pair.getDealer().isBlackjack(), GameResult.PUSH));  //default
        blackjack.add((pair) -> getGameResult(pair.getUser().isBlackjack(), !pair.getDealer().isBlackjack(), GameResult.BLACKJACK_USER_WIN));    //user_win
        blackjack.add((pair) -> getGameResult(!pair.getUser().isBlackjack(), pair.getDealer().isBlackjack(), GameResult.BLACKJACK_DEALER_WIN));   //deafult
        return blackjack;
    }
}
