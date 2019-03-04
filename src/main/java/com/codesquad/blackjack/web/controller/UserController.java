package com.codesquad.blackjack.web.controller;

import com.codesquad.blackjack.web.domain.WebUser;
import com.codesquad.blackjack.web.security.HttpSessionUtils;
import com.codesquad.blackjack.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/form")
    public String createForm() {
        return "/user/join";
    }

    @PostMapping("")
    public String create(HttpSession session, WebUser user) {
        WebUser loginUser = userService.add(user);
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY,loginUser);

        return "redirect:/";
    }

}
