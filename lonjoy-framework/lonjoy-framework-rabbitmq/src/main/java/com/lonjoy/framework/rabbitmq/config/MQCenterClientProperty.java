package com.lonjoy.framework.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luke
 * @date 2022/8/15 19:41
 * @desc
 **/


@ConfigurationProperties("mq.center.client")
public class MQCenterClientProperty {
    private final MQCenterClientProperty.Startup startup = new MQCenterClientProperty.Startup();
    private int handshakeTimeout = 10000;

    public MQCenterClientProperty() {
    }

    public int getHandshakeTimeout() {
        return this.handshakeTimeout;
    }

    public void setHandshakeTimeout(int handshakeTimeout) {
        this.handshakeTimeout = handshakeTimeout;
    }

    public MQCenterClientProperty.Startup getStartup() {
        return this.startup;
    }

    public static class Startup {
        private int order = 300000;
        private boolean autoDeclare = true;

        public Startup() {
        }

        public int getOrder() {
            return this.order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public boolean isAutoDeclare() {
            return this.autoDeclare;
        }

        public void setAutoDeclare(boolean autoDeclare) {
            this.autoDeclare = autoDeclare;
        }
    }
}
