package com.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointtExporter();
    }
}
