package com.codesquad.blackjack.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gameRoom")
public class GameRoomController {

    @GetMapping("/form")
    public String createForm() {
        return "/gameRoom/form";
    }

    @PostMapping("/create")
    public String create(String text) {
        Gameroom gameroom = gameRoomService.create(text);

        return String.format("redirect:/gameRoom/%d",gameroom.getId());;
    }

}
