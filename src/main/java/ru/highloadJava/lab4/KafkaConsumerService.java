package ru.highloadJava.lab4;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.highloadJava.lab4.dataModel.CryptoAggregatedData;

@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Kafka Listener для потребления сообщений из Kafka.
     */
    @KafkaListener(topics = "crypto_data_aggregated")
    public void handleKafkaMessage(@Payload CryptoAggregatedData message) {
        log.info("Получено сообщение из Kafka: {}", message);
        System.out.println("Consumed data: {" + message + "}");

        // Отправляем сообщение всем подписанным WebSocket клиентам
        this.messagingTemplate.convertAndSend("/topic/updates", message);
    }
}
