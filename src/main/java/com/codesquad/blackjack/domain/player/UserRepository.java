package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<WebUser, Long> {
    Optional<WebUser> findByUserId(String userId);
}
