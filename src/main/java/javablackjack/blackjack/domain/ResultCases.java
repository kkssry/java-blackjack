package javablackjack.blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ResultCases {

    public Map<Integer, BiFunction<Player, Player, GameResult>> burstCase() {
        Map<Integer, BiFunction<Player, Player, GameResult>> burst = new HashMap<>();
        burst.put(1, (user, dealer) -> getGameResult(user.isBurst(), !dealer.isBurst(), GameResult.DEALER_WIN, user));
        burst.put(2, (user, dealer) -> getGameResult(!user.isBurst(), dealer.isBurst(), GameResult.USUER_WIN, user));
        return burst;
    }

    public Map<Integer, BiFunction<Player, Player, GameResult>> blackjackCase() {
        Map<Integer, BiFunction<Player, Player, GameResult>> blackjack = new HashMap<>();
        blackjack.put(1, (user, dealer) -> getGameResult(user.isBlackjack(), dealer.isBlackjack(), GameResult.PUSH, user));
        blackjack.put(2, (user, dealer) -> getGameResult(user.isBlackjack(), !dealer.isBlackjack(), GameResult.USUER_WIN, user));
        blackjack.put(3, (user, dealer) -> getGameResult(!user.isBlackjack(), dealer.isBlackjack(), GameResult.DEALER_WIN, user));
        return blackjack;
    }

    public Map<Integer, BiFunction<Player, Player, GameResult>> winnerCaseWithUserStay() {
        Map<Integer, BiFunction<Player, Player, GameResult>> winner = new HashMap<>();
        winner.put(1, (user, dealer) -> getGameResult(!user.isTurn(), (user.score() == dealer.score()), GameResult.PUSH, user));
        winner.put(2, (user, dealer) -> getGameResult(!user.isTurn(), user.compareScore(dealer), GameResult.USUER_WIN, user));
        winner.put(3, (user, dealer) -> getGameResult(!user.isTurn(), dealer.compareScore(user), GameResult.DEALER_WIN, user));
        return winner;
    }

    private GameResult getGameResult(boolean case1, boolean case2, GameResult gameResult, Player user) {
        if (case1 && case2) {
            user.finishTurn();
            return gameResult;
        }
        return GameResult.DEAULF;
    }

}
