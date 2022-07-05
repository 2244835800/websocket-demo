package com.game.sendall;

//import com.google.gson.Gson;
import com.alibaba.fastjson.JSONObject;
import com.game.entity.UserOnlineInfo;
import com.game.scene.Scene;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Gjing
 **/
@ServerEndpoint("/scene/{sceneId}/{userId}")
@Component
@Slf4j
public class MyWebsocketServer {
    /**
     * 存放所有在线的客户端
     */

//    private Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session,@PathParam("sceneId") String sceneId,@PathParam("userId") String userId) {
        log.info("新链接: {},{},{}", session.getId(),sceneId,userId);
        switch (sceneId){
            case "1": Scene.clients1.put(session.getId(), session);
            break;
        }
        //将新用户存入在线的组

    }

    /**
     * 客户端关闭
     * @param session session
     */
    @OnClose
    public void onClose(Session session,@PathParam("sceneId") String sceneId,@PathParam("userId") String userId) {
        log.info("断开: {},{},{}", session.getId(),sceneId,userId);
        switch (sceneId){
            case "1":
                Scene.clients1.remove(session.getId());
                Map m1= new Hashtable();

                        UserOnlineInfo userOnlineInfo=new UserOnlineInfo();
                userOnlineInfo.setUserId(userId);
                userOnlineInfo.setType(2);
                this.sendAll(sceneId,JSONObject.toJSON(userOnlineInfo).toString());
                Scene.scene1JSONArray.removeIf(
                        jo -> ((JSONObject) jo).get("userId").equals(userId)
                );

                break;
        }

        //将掉线的用户移除在线的组里
    }

    /**
     * 发生错误
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable,@PathParam("userId")String userId){
        if(throwable.toString().equals("java.io.EOFException"))
            log.info("id:{},强制退出",userId);
        else
            throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(@PathParam("sceneId") String sceneId,String message) {
//        log.info("服务端收到客户端发来的消息: {}", message);
        this.sendAll(sceneId,message);
    }
    @OnMessage
    public void onMessage(byte[] messages, Session session) {
        try {
            System.out.println("接收到byte消息:"+new String(messages,"utf-8"));
            //返回信息
            String resultStr="{name:\"张三\",age:18,addr:\"上海浦东\"}";
            //发送字符串信息的 byte数组
            ByteBuffer bf= ByteBuffer.wrap(resultStr.getBytes("utf-8"));
            session.getBasicRemote().sendBinary(bf);
            //发送字符串
            //session.getBasicRemote().sendText("测试");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    public static void sendAll(String sceneId,String message) {
        switch (sceneId){
            case "1":
                for (Map.Entry<String, Session> sessionEntry : Scene.clients1.entrySet()) {
                    sessionEntry.getValue().getAsyncRemote().sendText(message);
                }
                break;
        }

    }

}
