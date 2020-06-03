package org.feeder.api.statistics.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopics {

  public static final String ITEM_VIEWED_TOPIC = "${feeder.kafka.topics.item-viewed}";

  public static final String GROUP_ID = "${spring.application.name}";
}
