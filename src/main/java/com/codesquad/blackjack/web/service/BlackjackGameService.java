package com.codesquad.blackjack.web.service;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.web.repository.BlackjackRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BlackjackGameService {

    @Resource(name = "blackjackRepository")
    private BlackjackRepository blackjackRepository;

    @Transactional
    public void save(BlackjackGame blackjackGame) {
        blackjackRepository.save(blackjackGame);
    }

    public BlackjackGame findById(int id) {
        return blackjackRepository.findById(id);
    }

    public BlackjackGame clear(int id) {
        return blackjackRepository.clear(id);
    }

    public List<BlackjackGame> findAll() {
        return blackjackRepository.findAll();
    }

    public BlackjackGame save(int id, BlackjackGame blackjackGame) {
       return blackjackRepository.save(id, blackjackGame);
    }

    /*
    public BlackjackGame startGame(int id, String userId, String Chip) {
        BlackjackGame blackjackGame = findById(id);
        blackjackGame
    }*/
}


