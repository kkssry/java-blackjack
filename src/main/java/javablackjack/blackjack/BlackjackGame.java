package javablackjack.blackjack;

import javablackjack.blackjack.domain.CardDeck;
import javablackjack.blackjack.domain.Player;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static org.slf4j.LoggerFactory.getLogger;


public class BlackjackGame {
    private static final Logger log = getLogger(BlackjackGame.class);

    private Player user;
    private Player dealer;


    private Map<Integer, BiFunction<Player, Player, GameResult>> blackjackCases;
    private Map<Integer, BiFunction<Player, Player, GameResult>> burstCases;
    private Map<Integer, BiFunction<Player, Player, GameResult>> winnerCase;


    // todo 이걸 밖으로 빼면 렌덤 태스트가 가능하다. 의존관계 분리
    private CardDeck cardDeck = new CardDeck();

    // 플레이어에게 넘겨

    private GameResult result = GameResult.DEAULF;
    // todo 인스턴스 변수 어떻게 하면 줄일 수 있을까?


    public void initUser(Player player, Player dealer) {
        this.user = player;
        this.dealer = dealer;
        blackjackCases = blackjackCase();
        burstCases = burstCase();
        winnerCase = winnerCaseWithUserStay();
    }

    private Map<Integer, BiFunction<Player, Player, GameResult>> burstCase() {
        Map<Integer, BiFunction<Player, Player, GameResult>> burst = new HashMap<>();
        burst.put(1, (user, dealer) -> getGameResult(user.isBurst(), !dealer.isBurst(), GameResult.DEALER_WIN));
        burst.put(2, (user, dealer) -> getGameResult(!user.isBurst(), dealer.isBurst(), GameResult.USUER_WIN));
        return burst;
    }

    public Map<Integer, BiFunction<Player, Player, GameResult>> blackjackCase() {
        Map<Integer, BiFunction<Player, Player, GameResult>> asdf = new HashMap<>();
        asdf.put(1, (user, dealer) -> getGameResult(user.isBlackjack(), dealer.isBlackjack(), GameResult.PUSH));
        asdf.put(2, (user, dealer) -> getGameResult(user.isBlackjack(), !dealer.isBlackjack(), GameResult.USUER_WIN));
        asdf.put(3, (user, dealer) -> getGameResult(!user.isBlackjack(), dealer.isBlackjack(), GameResult.DEALER_WIN));
        return asdf;
    }

    public Map<Integer, BiFunction<Player, Player, GameResult>> winnerCaseWithUserStay() {
        Map<Integer, BiFunction<Player, Player, GameResult>> asdf = new HashMap<>();
        asdf.put(1, (user, dealer) -> getGameResult(!isUserTurnTrue(), (user.score() == dealer.score()), GameResult.PUSH));
        asdf.put(2, (user, dealer) -> getGameResult(!isUserTurnTrue(), user.compareScore(dealer), GameResult.USUER_WIN));
        asdf.put(3, (user, dealer) -> getGameResult(!isUserTurnTrue(), dealer.compareScore(user), GameResult.DEALER_WIN));
        return asdf;
    }
    
    private GameResult getGameResult(boolean case1, boolean case2, GameResult gameResult) {
        if (case1 && case2) {
            user.finishTurn();
            return gameResult;
        }
        return GameResult.DEAULF;
    }
    //todo 줄이고싶다.

    public void startGame() {
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
    }

    //포비는 찾아 낼 것인가


    public String checkBlackjack() {
        checkResultCase(blackjackCases);

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
        // 1.유저가 버스트가 안나거나 2.유저가 블랙잭이 아니거나 3.딜러가 버스트 안나거나 4.딜러가 블랙잭이 아닐때
        // 점수 비교
        checkResultCase(winnerCase);

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
        checkResultCase(burstCases);
    }

    //todo : 리팩토링 해야함
    public void userChoiceHitOrStand(int choiceNumber) {
        if (choiceNumber == 1) {
            user.drawCard(cardDeck.drawCard());
            /////////
            /*if (user.isBurst()) {
                result = GameResult.DEALER_WIN;
                user.finishTurn();
            }*/
            checkResultCase(burstCases);
            showCard();
        }
        if (choiceNumber == 2) {
            user.finishTurn();
        }
    }

    public boolean isUserTurnTrue() {
        return user.isTurn();
    }

    public GameResult getResult() {
        return result;
    }
}
