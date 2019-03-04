package com.codesquad.blackjack.web.repository;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.card.CardDeckFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlackjackRepository {
    private List<BlackjackGame> blackjackGames;

    public BlackjackRepository() {
        this.blackjackGames = new ArrayList<>();
    }


    public void save(BlackjackGame blackjackGame) {
        this.blackjackGames.add(blackjackGame);
    }


    public BlackjackGame save(int id, BlackjackGame blackjackGame) {
       return this.blackjackGames.set(id -1, blackjackGame);
    }

    public BlackjackGame findById(long id) {
        return blackjackGames.get((int) id - 1);
    }

    public BlackjackGame clear(int id) {
        return blackjackGames.set(id - 1, new BlackjackGame(CardDeckFactory.create()));
    }

    public List<BlackjackGame> findAll() {
        return blackjackGames;
    }
}
