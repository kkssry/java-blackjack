package javablackjack.blackjack.domain;

import javablackjack.blackjack.util.NumberManager;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class Player {
    private static final Logger log = getLogger(Player.class);

    private Chip chip;
    //todo : 1급 객체로 리팩토링 해야함.
    private List<Card> cards = new ArrayList<>();
    private String name;
    private boolean playerTurn = true;

    public Player(String userName) {
        this.name = userName;
        chip = new Chip(NumberManager.INIT_CHIP);
    }

    public void drawCard(Card card) {
        this.cards.add(card);
        setOneA();
    }

    public int score() {
        return cards.stream().mapToInt(Card::getNum).sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public boolean isBurst() {
        return score() > NumberManager.BLACKJACK_NUMBER;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public boolean compareScore(Player otherPlayer) {
        return score() > otherPlayer.score();
    }

    public boolean isEqualScore(Player otherPlayer) {
        return score() == otherPlayer.score();
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

    public Chip getChip() {
        return chip;
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

    @Override
    public String toString() {
        return "Player{" +
                "chip=" + chip +
                ", cards=" + cards +
                ", name='" + name + '\'' +
                '}';
    }
}
