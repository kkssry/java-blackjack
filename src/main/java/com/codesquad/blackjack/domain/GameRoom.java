package com.codesquad.blackjack.domain;


import com.codesquad.blackjack.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class GameRoom extends AbstractEntity {
    private String title;

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
}
