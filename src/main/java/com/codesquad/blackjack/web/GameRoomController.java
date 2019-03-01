package com.codesquad.blackjack.web;

import com.codesquad.blackjack.domain.GameRoom;
import com.codesquad.blackjack.service.GameRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/gameRoom")
public class GameRoomController {

    @Resource(name = "gameRoomService")
    private GameRoomService gameRoomService;

    @GetMapping("")
    public String createForm() {
        return "/gameRoom/form";
    }

    @PostMapping("/create")
    public String create(String subject) {
        GameRoom gameRoom = gameRoomService.save(subject);

        return String.format("redirect:/gameRoom/%d", gameRoom.getId());
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        GameRoom gameRoom = gameRoomService.findById(id);
        model.addAttribute("gameRoom",gameRoom);
        return "/gameRoom/show";
    }
}
