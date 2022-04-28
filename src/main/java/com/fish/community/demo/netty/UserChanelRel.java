package com.fish.community.demo.netty;

import io.netty.channel.Channel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户id 和channel 的关联关系处理
 */
@Data
public class UserChanelRel {
    private HashMap<Long, Channel> manage = new HashMap<>();

    public void put(Long senderId,Channel channel){
        manage.put(senderId,channel);
    }

    public Channel get(Long senderId){
        return manage.get(senderId);
    }

}
