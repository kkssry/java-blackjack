package com.codesquad.blackjack.service;

import com.codesquad.blackjack.UnAuthorizedException;
import com.codesquad.blackjack.domain.GameRoom;
import com.codesquad.blackjack.domain.GameRoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameRoomService {

    @Resource(name = "gameRoomRepository")
    private GameRoomRepository gameRoomRepository;


    public GameRoom save(String text) {
        return gameRoomRepository.save(new GameRoom(text));
    }

    public GameRoom findById(long id) {
        return gameRoomRepository.findById(id).orElseThrow(UnAuthorizedException::new);
    }

    public List<GameRoom> findAll() {
        return gameRoomRepository.findAll();
    }
}
