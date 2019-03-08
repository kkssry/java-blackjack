package com.codesquad.blackjack.web.domain;


import com.codesquad.blackjack.domain.AbstractEntity;
import com.codesquad.blackjack.domain.GameResult;

import javax.persistence.Entity;

@Entity
public class GameRoom extends AbstractEntity {
    private String title;

    private GameResult gameResult = GameResult.DEFAULT;

    public GameRoom() {
    }

    public GameRoom(String text) {
        this.title = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
