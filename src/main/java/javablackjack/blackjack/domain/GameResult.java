package javablackjack.blackjack.domain;

public enum GameResult {
    BLACKJACK_USER_WIN("유저가 블랙잭으로 이겼습니다."),
    BLACKJACK_DEALER_WIN("딜러가 블랙잭으로 이겼습니다."),
    USER_WIN("유저가 이겼습니다."),
    DEALER_WIN("딜러가 아겼습니다."),
    PUSH("비겼습니다."),
    DEFAULT("");

    private String gameResult;


    GameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public String getGameResult() {
        return gameResult;
    }

    public boolean isDefault() {
        return this.equals(DEFAULT);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult='" + gameResult + '\'' +
                '}';
    }
}
