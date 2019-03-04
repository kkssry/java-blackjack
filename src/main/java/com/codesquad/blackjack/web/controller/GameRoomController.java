package com.codesquad.blackjack.web.controller;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.card.CardDeckFactory;
import com.codesquad.blackjack.web.domain.GameRoom;
import com.codesquad.blackjack.web.service.BlackjackGameService;
import com.codesquad.blackjack.web.service.GameRoomService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/gameRoom")
public class GameRoomController {
    private static final Logger log = getLogger(GameRoomController.class);

    @Resource(name = "gameRoomService")
    private GameRoomService gameRoomService;

    @Resource(name = "blackjackGameService")
    private BlackjackGameService blackjackGameService;


    @GetMapping("")
    public String createForm() {
        return "/gameRoom/form";
    }

    @PostMapping("/create")
    public String create(String subject) {
        GameRoom gameRoom = gameRoomService.save(subject);
        blackjackGameService.save(new BlackjackGame(CardDeckFactory.create()));
        log.debug("레퍼지토리확인 : {}",blackjackGameService.findById(1));
        return String.format("redirect:/gameRoom/%d", gameRoom.getId());
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model, HttpSession session) {
        session.setAttribute("room",id);
        GameRoom gameRoom = gameRoomService.findById(id);
        model.addAttribute("gameRoom",gameRoom);
        return "/gameRoom/show";
    }
}
