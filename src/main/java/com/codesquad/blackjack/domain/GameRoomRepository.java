package com.codesquad.blackjack.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom,Long> {
}
