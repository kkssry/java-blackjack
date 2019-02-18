package javablackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ResultCases {
    public static List<BiFunction<Player, Player, GameResult>> burstCase() {
        List<BiFunction<Player, Player, GameResult>> list = new ArrayList<>();
        list.add((user,dealer)->getGameResult(user.isBurst(), !dealer.isBurst(),GameResult.DEALER_WIN));
        list.add((user,dealer)->getGameResult(!user.isBurst(), dealer.isBurst(),GameResult.USUER_WIN));
        return list;
    }

    public static List<BiFunction<Player, Player, GameResult>> blackjackCase() {
        List<BiFunction<Player, Player, GameResult>> blackjack = new ArrayList<>();
        blackjack.add((user, dealer) -> getGameResult(user.isBlackjack(), dealer.isBlackjack(), GameResult.PUSH));  //default
        blackjack.add((user, dealer) -> getGameResult(user.isBlackjack(), !dealer.isBlackjack(), GameResult.USUER_WIN));    //user_win
        blackjack.add((user, dealer) -> getGameResult(!user.isBlackjack(), dealer.isBlackjack(), GameResult.DEALER_WIN));   //deafult
        return blackjack;
    }

    public static List<BiFunction<Player, Player, GameResult>> winnerCaseWithUserStay() {
        List<BiFunction<Player, Player, GameResult>> winner = new ArrayList<>();
        winner.add((user, dealer) -> getGameResult(!user.isTurn(), user.isEqualScore(dealer), GameResult.PUSH));
        winner.add((user, dealer) -> getGameResult(!user.isTurn(), user.compareScore(dealer), GameResult.USUER_WIN));
        winner.add((user, dealer) -> getGameResult(!user.isTurn(), dealer.compareScore(user), GameResult.DEALER_WIN));
        return winner;
    }

    private static GameResult getGameResult(boolean case1, boolean case2, GameResult gameResult) {
        if (case1 && case2) {
            return gameResult;
        }
        return GameResult.DEFAULT;
    }
}
