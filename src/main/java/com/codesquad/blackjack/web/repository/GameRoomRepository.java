package com.codesquad.blackjack.web.repository;

import com.codesquad.blackjack.web.domain.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom,Long> {
}
