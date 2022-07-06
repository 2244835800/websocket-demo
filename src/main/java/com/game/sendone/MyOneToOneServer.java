package com.game.sendone;

//import com.google.gson.Gson;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gjing
 **/
@ServerEndpoint("/test")
@Component
@Slf4j
public class MyOneToOneServer {
    private MyOneToOneServer myOneToOneServer;
    private String id;
    /**
     * 用于存放所有在线客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

//    private JSON json = new JSON();

    @OnOpen
    public void onOpen(Session session) {
        id=session.getId();
        log.info("有新的客户端上线: {}", session.getId());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        log.info("有客户端离线: {}", sessionId);
        clients.remove(sessionId);
        myOneToOneServer=null;
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        String sessionId = session.getId();
        if (clients.get(sessionId) != null) {
            log.info("发生了错误,移除客户端: {}", sessionId);
            clients.remove(sessionId);
        }
//        throwable.printStackTrace();
    }

    /**
     * 在消息
     *
     * @param message 消息 格式为{userId:"",message:""}
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端发来的消息: {}", message);

        this.sendTo(JSON.parseObject(message, Message.class));
    }


    /**
     * 发送消息
     *
     * @param message 消息对象
     */
    private void sendTo(Message message) {
        Session s = clients.get(message.getUserId());
        log.info(id+"向客户端发送数据，客户端ID: {}", message.getUserId());
        if (s != null) {
            try {
                s.getBasicRemote().sendText(id+"向客户端发送数据，客户端ID:"+message.getUserId()+","+message.getMessage());
//                s.getBasicRemote().sendText("hope 已成功发送");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
