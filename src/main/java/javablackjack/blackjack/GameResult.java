package javablackjack.blackjack;

import javablackjack.blackjack.domain.Player;

import java.util.Arrays;

public enum GameResult {
    USUER_WIN("유저가 이겼습니다."),
    DEALER_WIN("딜러가 아겼습니다."),
    PUSH("비겼습니다."),
    DEAULF("");

    private String gameResult;


    GameResult(String gameResult) {
        this.gameResult = gameResult;
    }

//    public static GameResult findResult() {
//        Arrays.stream(GameResult.values()).filter();
//    }


    public String getGameResult() {
        /*if (DEAULF.equals(this)) {
            this.adf();
        }*/
        return gameResult;
    }

    private void adf(Player user, Player dealer) {

    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult='" + gameResult + '\'' +
                '}';
    }
}
