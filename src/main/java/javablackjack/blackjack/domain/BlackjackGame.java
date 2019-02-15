package javablackjack.blackjack.domain;

import org.slf4j.Logger;

import java.util.Map;
import java.util.function.BiFunction;

import static org.slf4j.LoggerFactory.getLogger;


public class BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Player user;
    private Player dealer;

    // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다. 의존관계 분리
    private CardDeck cardDeck = new CardDeck();

    private GameResult result = GameResult.DEAULF;
    private ResultCases resultCases = new ResultCases();

    // todo 인스턴스 변수 어떻게 하면 줄일 수 있을까?

    public void initUser(Player player, Player dealer) {
        this.user = player;
        this.dealer = dealer;
    }

    //todo 줄이고싶다.
    public void startGame() {
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
    }

    public String checkBlackjack() {
        checkResultCase(resultCases.blackjackCase());

        System.out.println("딜러의 총합 : " + dealer.score());
        System.out.println("유저의 총합 : " + user.score());
        return result.getGameResult();
    }

    private void checkResultCase(Map<Integer, BiFunction<Player, Player, GameResult>> cases) {
        for (BiFunction<Player, Player, GameResult> value : cases.values()) {
            if (result.equals(GameResult.DEAULF)) {
                result = value.apply(user, dealer);
            }
        }
    }

    // todo : 유저가 모두 스탠드 할때 총합 출력용도 view 단으로 이동
    public void showCard() {
        System.out.println(user.getName());
        System.out.println(user.getCards());
        System.out.println("----------------");
        System.out.println(dealer.getName());
        System.out.println(dealer.getCards());
    }


    //todo view 단으로 이동, refactoring
    public void winner() {
        System.out.println();

        checkResultCase(resultCases.winnerCaseWithUserStay());

        System.out.println("딜러의 총합 : " + dealer.score());
        System.out.println("유저의 총합 : " + user.score());
    }

    //todo : 리팩토링 해야함
    public void dealerTurn() {
        if (result.equals(GameResult.DEAULF)) {
            while (dealer.score() < 17) {
                dealer.drawCard(cardDeck.drawCard());
                showCard();
            }
        }
        checkResultCase(resultCases.burstCase());
    }

    //todo : 리팩토링 해야함
    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());
            checkResultCase(resultCases.burstCase());
            showCard();
        }
        if (choiceNumber == 2) {
            user.finishTurn();
        }
    }


    public GameResult getResult() {
        return result;
    }

    public boolean isUserTurn() {
        return user.isTurn();
    }
}
