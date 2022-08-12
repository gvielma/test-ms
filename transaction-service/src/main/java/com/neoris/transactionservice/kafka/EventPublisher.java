package com.neoris.transactionservice.kafka;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {
	
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    
    
    public ListenableFuture<SendResult<Integer, String>> sendEvent(Long transactionID, Object transaction, String topicName)
            throws JsonProcessingException {

        Integer key = transactionID.intValue();
        String value = objectMapper.writeValueAsString(transaction);

        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topicName);
        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {

                try {
                    handleSuccess(key, value, result);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }
        });
        return listenableFuture;
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("transaction-header-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("An error occurred while sending the message: {}", ex.getMessage());
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result)
            throws JsonProcessingException {

        log.info("Message Sent Successfully for the key: {} and the value is: {}, partition is: {}", key, value,
                result.getRecordMetadata().partition());
    }

}
