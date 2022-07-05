package com.game.scene;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2020/4/1
 * @Time:9:45
 */
public class Scene {
    public static JSONArray scene1JSONArray = new JSONArray();
    public static JSONArray scene2JSONArray = new JSONArray();
    public static JSONArray scene3JSONArray = new JSONArray();
    public static JSONArray scene4JSONArray = new JSONArray();
    public static JSONArray scene5JSONArray = new JSONArray();
    public static JSONArray scene6JSONArray = new JSONArray();
    public static JSONArray scene7JSONArray = new JSONArray();
    public static JSONArray scene8JSONArray = new JSONArray();

    public static Map<String, Session> clients1 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients2 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients3 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients4 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients5 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients6 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients7 = new ConcurrentHashMap<>();
    public static Map<String, Session> clients8 = new ConcurrentHashMap<>();
//    public static HashMap map1=new HashMap();
//    public static Map<String, JSON> clients1 = new ConcurrentHashMap<>();
//    public static Map<String, JSON> clients2 = new ConcurrentHashMap<>();
//    public static Map<String, JSON> clients3 = new ConcurrentHashMap<>();
//    public static Map<String, JSON> clients4 = new ConcurrentHashMap<>();
//    public static Map<String, JSON> clients5 = new ConcurrentHashMap<>();
}
