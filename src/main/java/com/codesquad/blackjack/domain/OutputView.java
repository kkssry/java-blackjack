package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.player.Pair;

public class OutputView {
    Pair pair;
    GameResult gameResult;

    public OutputView() {
    }

    public OutputView(Pair pair, GameResult gameResult) {
        this.pair = pair;
        this.gameResult = gameResult;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
