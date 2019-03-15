package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.util.NumberManager;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class User extends AbstractPlayer {
    private static final Logger log = getLogger(User.class);


    private Chip bettingChip;
    //todo : 1급 객체로 리팩토링 해야함.

    public User(String userName) {
        this(userName, new Chip(NumberManager.INIT_CHIP));
    }

    public User(String name, Chip chip) {
        super(name);
        this.bettingChip = chip;
    }

    public void loseChip() {
        this.bettingChip = this.bettingChip.minus(bettingChip);
    }

    public Chip winningChip() {
        return this.bettingChip = this.bettingChip.plus(bettingChip).plus(bettingChip); //배팅한거 회수
    }

    public Chip pushChip() {
        return this.bettingChip.plus(bettingChip);
    }

    public Chip blackjackChip() {
        return this.bettingChip = this.bettingChip.plus(bettingChip).blackjackPlus(bettingChip);
    }

    public Chip getBettingChip() {
        return bettingChip;
    }

    public void setBettingChip(Chip bettingChip) {
        this.bettingChip = bettingChip;
    }

    @Override
    public String toString() {
        return super.toString() + "chip=" + bettingChip;

    }

}
