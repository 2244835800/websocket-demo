package com.game;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.game.entity.UserOnlineInfo;
import com.game.scene.Scene;
import com.game.sendall.MyWebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2020/3/31
 * @Time:22:56
 */
@Controller
@Slf4j
public class UserOnlineInfoController {

    /**
     * 玩家上线,通知其他玩家创建模型
     * @param request
     * @return
     */
    @PostMapping("/showInScene/{sceneId}")
    @ResponseBody
    public String firstShowInScene(HttpServletRequest request, @PathVariable("sceneId") String sceneId){
        log.info("firstShowInScene",sceneId);
        String userId = request.getParameter("userId");
        String bodyPrefab = request.getParameter("bodyPrefab");
        String weapon1 = request.getParameter("weapon1");
        String weapon2 = request.getParameter("weapon2");
        String _transform = request.getParameter("_transform");
        String weapon1Location = request.getParameter("weapon1Location");
        String weapon2Location = request.getParameter("weapon2Location");
        int type = Integer.parseInt(request.getParameter("type"));
        UserOnlineInfo userinfo = new UserOnlineInfo(type,userId,bodyPrefab,weapon1,weapon2,_transform,weapon1Location,weapon2Location);
        JSONObject json= (JSONObject) JSON.toJSON(userinfo);
//        json.put("type",0);
        switch (sceneId){
            case "1":
                Scene.scene1JSONArray.add(json);
                break;
        }
        MyWebsocketServer.sendAll(sceneId,json.toJSONString());
//        Scene.clients1.put(userId, (JSON)JSON.toJSON(userinfo));
        return "yesa";
    }

    /**
     * 该玩家要创建地图上其他玩家模型
     * @param
     * @param sceneId
     * @return
     */
    @RequestMapping("/getModelInfo/{sceneId}")
    @ResponseBody
    public String getModelInfo(@PathVariable("sceneId") String sceneId){
        log.info("进入场景{}",sceneId);
        JSONObject jo=new JSONObject();
        switch (sceneId){
            case "1":
                jo.put("data",Scene.scene1JSONArray);
                break;
        }
        return jo.toJSONString();
    }

}
