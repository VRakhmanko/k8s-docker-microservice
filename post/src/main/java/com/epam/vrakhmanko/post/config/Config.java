package com.epam.vrakhmanko.post.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableAutoConfiguration
public class Config {
    @Value("${clients.service.users.baseurl}")
    private String USERS_SERVICE_BASE_URL;

    @Value("${clients.timeout}")
    private int TIMEOUT;

    @Value("${clients.codecs.max-memory-size}")
    private int MAX_MEMORY_SIZE;

    @Bean
    public WebClient userClient() {
        return WebClient.builder()
                .baseUrl(USERS_SERVICE_BASE_URL)
//                .baseUrl("http://user-service:18080/users/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(
                        clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                                .maxInMemorySize(MAX_MEMORY_SIZE))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(getTcpClient())))
                .build();
    }

    private TcpClient getTcpClient() {
        return TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });
    }
}
