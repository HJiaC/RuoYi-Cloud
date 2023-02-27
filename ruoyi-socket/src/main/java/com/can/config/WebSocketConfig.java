package com.can.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @filename: WebsocketConfig
 * @author: HXJ
 * @date: 2023/2/27 7:09 PM
 * @version: V1.0
 * @description:
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();

    }
}