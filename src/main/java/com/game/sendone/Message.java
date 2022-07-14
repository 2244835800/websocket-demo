package com.game.sendone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gjing
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String content;


    private long receiverId;//接收方id
    private long senderId;//发送方id
    //接受状态值
    public static final int TYPE_RECEIVED = 0;
    //发送状态值
    public static final int TYPE_SENT = 1;
    //判断类型值
    private int type;
    //判断消息是否已读
    private int read;
    private String Filename;
    private String FilePath;
    private int msgType;


}
