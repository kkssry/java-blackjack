package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.card.CardDeckFactory;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.io.Input;
import com.codesquad.blackjack.io.OutputResult;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        GameResult result;

        Input input = new Input();

        BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
        blackjackGame.initUser(new User(input.getString()), new Dealer("dealer"));

        OutputResult.bettingChip();

        Chip bettingChip = new Chip(input.getint());
        blackjackGame.startGame(bettingChip);

        OutputResult.showCard(blackjackGame.getPair());

        ///1. 블랙잭으로 승패확인 승패가 나면 턴종료, 아니면 result = 디폴트
        result = blackjackGame.checkBlackjack();
        blackjackGame.playerTurnFinish(result);


        //유저턴
        ///2. 유저버스트로 승패확인 승패가 나면 턴종료, 아니면 result = 디폴트
        while (blackjackGame.isUserTurn()) {
            result = blackjackGame.userChoiceHitOrStand(input.choiceHitOrstand());
            blackjackGame.playerTurnFinish(result);
            OutputResult.showCard(blackjackGame.getPair());
        }

        // 딜러턴
        ///3. 딜러버스트로 승패확인 승패가 나면 턴종료, 아니면 result = 디폴트
        while (blackjackGame.isDealerTurn()) {
            result = blackjackGame.dealerTurn();
            blackjackGame.playerTurnFinish(result);
            OutputResult.showCard(blackjackGame.getPair());
        }

        // 모든 턴종료시 스코어 비교해서 승패 결정
        if (result.isDefault()) {
            result = blackjackGame.winner();
        }

        blackjackGame.manageChip(result, bettingChip);
        OutputResult.showWinner(blackjackGame);
        OutputResult.resultPrint(result);
    }
}
