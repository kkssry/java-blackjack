package javablackjack.blackjack.domain.cases;

import javablackjack.blackjack.domain.GameResult;
import javablackjack.blackjack.domain.Pair;

import java.util.List;
import java.util.function.Function;

public interface ResultCases {
    List<Function<Pair, GameResult>> caseList();

}
