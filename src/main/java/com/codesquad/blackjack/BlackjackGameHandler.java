package com.codesquad.blackjack;

import org.apache.catalina.manager.util.SessionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.NativeWebRequest;
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
        webSocketSessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("ggggg");
        String decodedData = URLDecoder.decode(message.getPayload(), "UTF-8");
        log.debug("dsfasdf {}", decodedData);

        // 유저가 히트 했습니다.
        for (WebSocketSession session1 : webSocketSessions.values()) {
            session1.sendMessage(new TextMessage(session.getId() + ": " + decodedData));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("세션종료");
        webSocketSessions.remove(session.getId());
    }
}
