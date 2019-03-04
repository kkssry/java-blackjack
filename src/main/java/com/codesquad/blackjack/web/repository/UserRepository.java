package com.codesquad.blackjack.web.repository;

import com.codesquad.blackjack.web.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<WebUser, Long> {
    Optional<WebUser> findByUserId(String userId);
}
