package com.codesquad.blackjack.web.service;

import com.codesquad.blackjack.Exception.UnAuthenticationException;
import com.codesquad.blackjack.Exception.UnAuthorizedException;
import com.codesquad.blackjack.web.domain.WebUser;
import com.codesquad.blackjack.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public WebUser add(WebUser user) {
        return userRepository.save(user);
    }

    public WebUser findById(long id) {
        return userRepository.findById(id).orElseThrow(UnAuthorizedException::new);
    }

    public List<WebUser> findAll() {
        return userRepository.findAll();
    }

    public WebUser login(String userId, String password) throws UnAuthenticationException {
        Optional<WebUser> maybeUser = userRepository.findByUserId(userId);
        if (!maybeUser.isPresent()) {
            throw new UnAuthenticationException("아이디가 존제하지 않습니다.");
        }

        WebUser user = maybeUser.get();
        if (!user.matchPassword(password)) {
            throw new UnAuthenticationException("패스워드가 다릅니다.");
        }

        return user;
    }
}
