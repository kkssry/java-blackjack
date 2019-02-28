package com.codesquad.blackjack.domain.cases;

import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.player.Pair;

import java.util.List;
import java.util.function.Function;

public interface ResultCases {
    List<Function<Pair, GameResult>> caseList();

}
