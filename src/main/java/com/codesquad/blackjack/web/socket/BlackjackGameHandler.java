package com.codesquad.blackjack.web.socket;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.GameResult;
import com.codesquad.blackjack.domain.OutputView;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.web.domain.WebUser;
import com.codesquad.blackjack.web.service.BlackjackGameService;
import com.codesquad.blackjack.web.service.GameRoomService;
import com.codesquad.blackjack.web.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Component
public class BlackjackGameHandler extends TextWebSocketHandler {
    private static final Logger log = LogManager.getLogger(BlackjackGameHandler.class);

    Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

    @Autowired
    private BlackjackGameService blackjackGameService;

    @Autowired
    private GameRoomService gameRoomService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("소켓생성");
        webSocketSessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String decodedData = URLDecoder.decode(message.getPayload(), "UTF-8");

        Map<String, Object> map = session.getAttributes();
        WebUser user = (WebUser) map.get("loginedUser");
        String[] messageFromJS = decodedData.split("=");

        GameResult result = GameResult.DEFAULT;
        //리팩터링 해야함
        Long room = (Long) map.get("room");
        int index = room.intValue();
        Chip bettingChip;

        if (messageFromJS[0].equals("bettingchip")) {
            bettingChip = new Chip(Integer.parseInt(messageFromJS[1]));
            BlackjackGame blackjackGame = blackjackGameService.clear(index)
                    .initUser(new User(user.getUserId(),bettingChip), new Dealer("dealer"))
                    .startGame();

            log.debug("블랙잭 1: {}", blackjackGame);
            user.setChip(user.getChip().minus(bettingChip));
            blackjackGameService.save(index, blackjackGame);


            result = blackjackGame.checkBlackjack();
            blackjackGame.playerTurnFinish(result);
            // 유저 카드 출력

            blackjackGame.manageChip(result);
            log.debug(" 배팅 칩 : {}", blackjackGame.getPair().getUser().getBettingChip());
            Chip chip = user.getChip().plus(blackjackGame.getPair().getUser().getBettingChip());
            log.debug("칩 : {} ", chip.toString());
            if (result != GameResult.DEFAULT) {
                user.setChip(chip);
            }
            userService.add(user);
            OutputView abc = new OutputView(blackjackGame.getPair(),result);
            String json = objectMapper.writeValueAsString(abc);
            for (WebSocketSession value : webSocketSessions.values()) {
                if (value.getAttributes().get("room").equals(room)) {
                    value.sendMessage(new TextMessage(json));
                }
            }
            return;
        }
        ///////////////////////////////////////////////


        if (messageFromJS[0].equals("hitOrStand")) {
            BlackjackGame blackjackGame = blackjackGameService.findById(index);
            log.debug("블랙잭 2: {}", blackjackGame);
            log.debug("페어페어 : {} ", blackjackGame.getPair());
//             GameResult result = GameResult.DEFAULT;
            log.debug("blackjack game is userTurn : {}", blackjackGame.isUserTurn());
            if (blackjackGame.isUserTurn()) {
                if (messageFromJS[1].equals("hit")) {
                    result = blackjackGame.userChoiceHitOrStand(1);
                }

                if (messageFromJS[1].equals("stand")) {
                    result = blackjackGame.userChoiceHitOrStand(2);
                    while (blackjackGame.isDealerTurn()) {
                        result = blackjackGame.dealerTurn();
                        blackjackGame.playerTurnFinish(result);
                    }

                    if (result.isDefault()) {
                        result = blackjackGame.winner();
                    }
                }


                blackjackGame.manageChip(result);
                log.debug(" 배팅 칩 : {}", blackjackGame.getPair().getUser().getBettingChip());

                Chip chip = user.getChip().plus(blackjackGame.getPair().getUser().getBettingChip());
                if (result != GameResult.DEFAULT) {
                    user.setChip(chip);
                }
                log.debug("칩 : {} ", chip.toString());
                userService.add(user);


                OutputView abc = new OutputView(blackjackGame.getPair(),result);
                String json = objectMapper.writeValueAsString(abc);
                log.debug("제이슨스트링으로 변환 : {}",json);
                for (WebSocketSession value : webSocketSessions.values()) {
                    if (value.getAttributes().get("room").equals(room)) {
                        //sendMesesage : 클라이언트 화면에 출력
                        value.sendMessage(new TextMessage(json));
                       // value.sendMessage(new TextMessage("result=" + result.getGameResult()));

                    }
                }


            }
            return;
        }

        // 유저가 히트 했습니다.


        for (WebSocketSession session1 : webSocketSessions.values()) {
            if (user.getUserId() != null) {
                session1.sendMessage(new TextMessage(user.getUserId() + ": " + decodedData));

            } else {
                session1.sendMessage(new TextMessage(session.getId() + ": " + decodedData));

            }
        }
//
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("세션종료");
        webSocketSessions.remove(session.getId());
    }
}
