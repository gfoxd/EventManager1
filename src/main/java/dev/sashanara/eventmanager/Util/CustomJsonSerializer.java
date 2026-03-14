package dev.sashanara.eventmanager.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomJsonSerializer<T> implements Serializer<T> {

    private ObjectMapper objectMapper;

    // Пустой конструктор для Kafka
    public CustomJsonSerializer() {}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Настраиваем ObjectMapper, если нужно
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сериализации JSON", e);
        }
    }

    @Override
    public void close() {}
}
