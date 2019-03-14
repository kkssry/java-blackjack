package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.util.NumberManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    private List<Card> cards = new ArrayList<>();
    private String name;
    private boolean playerTurn = true;

    public AbstractPlayer(String name) {
        this.name = name;
    }

    public void drawCard(Card card) {
        this.cards.add(card);
        setOneA();
    }

    public int score() {
        return cards.stream().mapToInt(Card::bringNum).sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBurst() {
        return score() > NumberManager.BLACKJACK_NUMBER;
    }

    public boolean compareScore(AbstractPlayer otherPlayer) {
        return score() > otherPlayer.score();
    }

    public void setOneA() {
        if (this.isBurst() && this.isContainElevenA()) {
            cards.get(cards.indexOf(Card.ELEVEN_A)).setIsOneA();
        }
    }

    public boolean isContainElevenA() {
        return cards.contains(Card.ELEVEN_A);
    }

    public boolean isBlackjack() {
        return score() == NumberManager.BLACKJACK_NUMBER;
    }

    public void finishTurn() {
        playerTurn = false;
    }

    public boolean isTurn() {
        return playerTurn;
    }

    public boolean isBlackjackWin(AbstractPlayer other) {
        //this 가 블래잭이고 other 가 블랙잭 아니면 트루
        return this.isBlackjack() && !other.isBlackjack();
    }

    public boolean isWin(AbstractPlayer other) {
        if (this.isTurn()) {
            return this.isBlackjackWin(other);
        }
        return this.compareScore(other);
    }

    public boolean isPush(AbstractPlayer other) {
        if (this.isTurn()) {
            return this.isBlackjack() && other.isBlackjack();
        }
        return this.score() == other.score();

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "cards=" + cards +
                ", name='" + name + '\'' +
                ", playerTurn=" + playerTurn +
                '}';
    }


}
