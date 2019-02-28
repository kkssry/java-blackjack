package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.BaseTest;
import org.junit.Test;

public class ChipTest extends BaseTest {
    @Test
    public void chipTest() {
        Chip chip = new Chip(500);

        softly.assertThat(chip.getChip()).isEqualTo(500);
    }

    @Test
    public void chipMinus() {
        Chip chip = new Chip(500);
        softly.assertThat(chip.minus(new Chip(300)).getChip()).isEqualTo(200);
    }

    @Test
    public void chipPlus() {
        Chip chip = new Chip(500);
        softly.assertThat(chip.plus(new Chip(300)).getChip()).isEqualTo(800);
    }
}