package com.codesquad.blackjack.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Chip {
    private int chip;

    public Chip() {
    }

    public Chip(int chip) {
        this.chip = chip;
    }

    public int getChip() {
        return chip;
    }

    public Chip minus(Chip bettingChip) {
        return new Chip(this.chip - bettingChip.chip);
    }

    public Chip plus(Chip bettingChip) {
        return new Chip(this.chip + bettingChip.chip);
    }

    public Chip blackjackPlus(Chip bettingChip) {
        return new Chip((int) (this.chip + (bettingChip.chip * 1.5)));
    }

    @Override
    public String toString() {
        return "Chip{" +
                "chip=" + chip +
                '}';
    }
}
