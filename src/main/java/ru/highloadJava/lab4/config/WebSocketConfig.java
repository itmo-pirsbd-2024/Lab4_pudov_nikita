package ru.highloadJava.lab4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
    Добавляем endpoint для подключения по Websocket.
    Регистрирует новый WebSocket-эндпоинт по указанному пути.
    В данном случае клиенты будут подключаться к WebSocket-серверу через URL /ws.
    Например: ws://<ваш-сервер>/crypto.
    */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/crypto").setAllowedOriginPatterns("*").withSockJS();;
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        messageConverters.add(new MappingJackson2MessageConverter());
        return true;
    }


    /*
    Конфигурируем некий брокер в Websocket, при обращении по пути /topic/..
    все подписанные клиенты получают сообщения с этого топика.
    config.setApplicationDestinationPrefixes определяет префиксы для сообщений,
    которые клиент отправляет серверу.
    Это для обработки клиентских запросов на сервере
    (например, если бы клиент хотел отправить данные серверу, используя /app/someEndpoint).
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
