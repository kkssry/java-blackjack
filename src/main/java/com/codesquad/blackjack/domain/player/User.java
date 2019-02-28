package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.util.NumberManager;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class User extends AbstractPlayer {
    private static final Logger log = getLogger(User.class);

    private Chip chip;
    //todo : 1급 객체로 리팩토링 해야함.

    public User(String userName) {
        super(userName);
        chip = new Chip(NumberManager.INIT_CHIP);
    }

    public void betting(Chip chip) {
        this.chip = this.chip.minus(chip);
    }

    public Chip winningChip(Chip chip) {
        return this.chip = this.chip.plus(chip).plus(chip); //배팅한거 회수
    }

    public Chip pushChip(Chip chip) {
        return this.chip = this.chip.plus(chip);
    }

    public Chip blackjackChip(Chip chip) {
        return this.chip = this.chip.plus(chip).blackjackPlus(chip);
    }

    public Chip getChip() {
        return chip;
    }

    @Override
    public String toString() {
        return super.toString() + "chip=" + chip;

    }

}
