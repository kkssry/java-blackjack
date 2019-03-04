package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.BlackjackGame;
import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.WebUser;
import com.codesquad.blackjack.domain.card.CardDeckFactory;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class BlackjackGameHandler extends TextWebSocketHandler {
    private static final Logger log = LogManager.getLogger(BlackjackGameHandler.class);


    Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

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
        String decodedData = URLDecoder.decode(message.getPayload(), "UTF-8");
        log.debug("dsfasdf {}", decodedData);

        Map<String, Object> map = session.getAttributes();
        WebUser user = (WebUser) map.get("loginedUser");
        log.debug("유저아이디 : {} ", user.getUserId());
        String[] a = decodedData.split(" ");

        log.debug("a : {}", a);

        if (a[0].equals("bettingchip")) {
            BlackjackGame blackjackGame = new BlackjackGame(CardDeckFactory.create());
            blackjackGame.initUser(new User(user.getUserId()), new Dealer("dealer"));
            blackjackGame.startGame(new Chip(Integer.parseInt(a[1])));


            // 유저 카드 출력
            for (WebSocketSession value : webSocketSessions.values()) {
                if (value.getAttributes().get("room").equals(map.get("room"))) {
                    value.sendMessage(new TextMessage("user:" + blackjackGame.getPair().getUser().getCards().toString()));
                    value.sendMessage(new TextMessage("dealer:" + blackjackGame.getPair().getUser().getCards().toString()));//sendMesesage : 클라이언트 화면에 출력
                    return;
                }
            }
        }

        // 유저가 히트 했습니다.


        for (WebSocketSession session1 : webSocketSessions.values()) {
            if (user.getUserId() != null) {
                session1.sendMessage(new TextMessage(user.getUserId() + ": " + decodedData));

            } else {
                session1.sendMessage(new TextMessage(session.getId() + ": " + decodedData));

            }
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("세션종료");
        webSocketSessions.remove(session.getId());
    }
}
