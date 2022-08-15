package com.lonjoy.framework.rabbitmq;

import com.lonjoy.framework.rabbitmq.model.SendMessageRequest;
import com.lonjoy.framework.rabbitmq.service.AutoDeclareStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luke
 * @date 2022/8/15 21:54
 * @desc
 **/

public class OnceAutoDeclareStrategy implements AutoDeclareStrategy {
    public static final OnceAutoDeclareStrategy INSTANCE = new OnceAutoDeclareStrategy();
    private final Map<String, Boolean> map = new ConcurrentHashMap();

    public OnceAutoDeclareStrategy() {
    }

    public boolean isAutoDeclare(SendMessageRequest request) {
        String key = this.newKey(request);
        if (this.map.containsKey(key)) {
            return false;
        } else {
            this.map.put(key, Boolean.TRUE);
            return true;
        }
    }

    private String newKey(SendMessageRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append('/').append(request.getHost());
        builder.append('/').append(request.getExchange());
        builder.append('/').append(request.getRoutingKey());
        builder.append('/').append(request.getQueue());
        return builder.toString();
    }

    public void sendMessageFailed(SendMessageRequest request, Exception e) {
        String key = this.newKey(request);
        this.map.remove(key);
    }
}
