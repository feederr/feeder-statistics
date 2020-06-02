package org.feeder.api.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaTestConsumer {

  @KafkaListener(topics = "test", groupId = "${spring.application.name}")
  public void consumeTestMessage(TestClass message) {
    log.debug("Message consumed:\n{}", message);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TestClass {
    private String message;
  }
}
