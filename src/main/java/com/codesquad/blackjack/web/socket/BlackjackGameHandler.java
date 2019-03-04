package com.codesquad.blackjack.web.socket;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.web.domain.WebUser;
import com.codesquad.blackjack.web.service.BlackjackGameService;
import com.codesquad.blackjack.web.service.GameRoomService;
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
    Map<Long, BlackjackGame> gameMap = new HashMap<>();

    @Autowired
    private BlackjackGameService blackjackGameService;

    @Autowired
    private GameRoomService gameRoomService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("소켓생성");
        log.debug("나는 websession 입니다.{}", session.getId());
        log.debug("나는 어트리뷰트 입니다.{}", session.getAttributes());


        log.debug("URL은 : {}", session.getUri());
        log.debug("프로토콜 : {}", session.getAcceptedProtocol());

        Map<String, Object> map = session.getAttributes();
        WebUser user = (WebUser) map.get("loginedUser");
        log.debug("유저아이디 : {} ", user.getUserId());

        webSocketSessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("나는 어트리뷰트 입니다.{}", session.getAttributes());
        String decodedData = URLDecoder.decode(message.getPayload(), "UTF-8");
        log.debug("입력받은 메시지 :{}", decodedData);
        log.debug("게임룸 리파지토리 {}",gameRoomService.findAll());

        log.debug("게임룸 리파지토리 {}",blackjackGameService.findAll());

        Map<String, Object> map = session.getAttributes();
        WebUser user = (WebUser) map.get("loginedUser");
        log.debug("유저아이디 : {} ", user.getUserId());
        String[] a = decodedData.split("=");

        log.debug("a : {}", a);
        //////////////////////////////////////////
        log.debug("타입확인 : {}",map.get("room").getClass().getName());
        //리팩터링 해야함
        Long room = (Long)map.get("room");
        int index = room.intValue();

        log.debug("index : {}",index);

        if (a[0].equals("bettingchip")) {

            BlackjackGame blackjackGame = blackjackGameService.clear(index)
                    .initUser(new User(user.getUserId()), new Dealer("dealer"))
                    .startGame(new Chip(Integer.parseInt(a[1])));
            log.debug("블랙잭 1: {}", blackjackGame);
            blackjackGameService.save(index,blackjackGame);


            blackjackGame.checkBlackjack();


            BlackjackGame blackjackGame1 = blackjackGameService.findById(index);
            log.debug("블랙잭 id : {}", blackjackGame1);

            log.debug("블랙잭 파인드바이아이디 : {}", blackjackGame1.getPair());
            // 유저 카드 출력
            for (WebSocketSession value : webSocketSessions.values()) {
                if (value.getAttributes().get("room").equals(room)) {
                    value.sendMessage(new TextMessage("user=" + blackjackGame.getPair().getUser().getCards().toString()));
                    value.sendMessage(new TextMessage("dealer=" + blackjackGame.getPair().getDealer().getCards().get(0).toString()));//sendMesesage : 클라이언트 화면에 출력
                }
            }
            return;
        }
        ///////////////////////////////////////////////


        if (a[0].equals("hitOrStand")) {
            BlackjackGame blackjackGame = blackjackGameService.findById(index);
            log.debug("블랙잭 2: {}", blackjackGame);
            log.debug("페어페어 : {} ", blackjackGame.getPair());
            if (a[1].equals("hit")) {
                blackjackGame.userChoiceHitOrStand(1);
            }

            if (a[1].equals("stand")) {
                blackjackGame.userChoiceHitOrStand(2);
            }
            for (WebSocketSession value : webSocketSessions.values()) {
                if (value.getAttributes().get("room").equals(room)) {
                    value.sendMessage(new TextMessage("user=" + blackjackGame.getPair().getUser().getCards().toString()));
                    value.sendMessage(new TextMessage("dealer=" + blackjackGame.getPair().getDealer().getCards().get(0).toString()));//sendMesesage : 클라이언트 화면에 출력
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
