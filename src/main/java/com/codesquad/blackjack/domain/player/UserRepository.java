package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<WebUser, Long> {
    //Optional<WebUser> findByUserId(String userId);
}
