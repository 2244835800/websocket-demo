package com.game.entity;

import lombok.Data;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2020/4/1
 * @Time:9:48
 */
public class UserOnlineInfo {
    public int type;
    public String userId;
    public String bodyPrefab;
    public String weapon1;
    public String weapon2;
    public String _transform;
    public String weapon1Location;
    public String weapon2Location;

    public UserOnlineInfo() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getBodyPrefab() {
        return bodyPrefab;
    }

    public void setBodyPrefab(String bodyPrefab) {
        this.bodyPrefab = bodyPrefab;
    }

    public String getWeapon1() {
        return weapon1;
    }

    public void setWeapon1(String weapon1) {
        this.weapon1 = weapon1;
    }

    public String getWeapon2() {
        return weapon2;
    }

    public void setWeapon2(String weapon2) {
        this.weapon2 = weapon2;
    }

    public String get_transform() {
        return _transform;
    }

    public void set_transform(String _transform) {
        this._transform = _transform;
    }

    public String getWeapon1Location() {
        return weapon1Location;
    }

    public void setWeapon1Location(String weapon1Location) {
        this.weapon1Location = weapon1Location;
    }

    public String getWeapon2Location() {
        return weapon2Location;
    }

    public void setWeapon2Location(String weapon2Location) {
        this.weapon2Location = weapon2Location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserOnlineInfo(int type, String userId, String bodyPrefab, String weapon1, String weapon2, String _transform, String weapon1Location, String weapon2Location) {
        this.type = type;
        this.userId = userId;
        this.bodyPrefab = bodyPrefab;
        this.weapon1 = weapon1;
        this.weapon2 = weapon2;
        this._transform = _transform;
        this.weapon1Location = weapon1Location;
        this.weapon2Location = weapon2Location;
    }


}
