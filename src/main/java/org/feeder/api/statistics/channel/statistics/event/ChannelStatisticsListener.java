package org.feeder.api.statistics.channel.statistics.event;

import static org.feeder.api.statistics.common.KafkaTopics.CHANNEL_REMOVED_TOPIC;
import static org.feeder.api.statistics.common.KafkaTopics.CHANNEL_SUBSCRIBED_TOPIC;
import static org.feeder.api.statistics.common.KafkaTopics.CHANNEL_UNSUBSCRIBED_TOPIC;
import static org.feeder.api.statistics.common.KafkaTopics.GROUP_ID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.statistics.channel.statistics.service.ChannelStatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelStatisticsListener {

  private final ChannelStatisticsService service;

  @Value(CHANNEL_SUBSCRIBED_TOPIC)
  private String channelSubscribedTopic;

  @Value(CHANNEL_UNSUBSCRIBED_TOPIC)
  private String channelUnsubscribedTopic;

  @Value(CHANNEL_REMOVED_TOPIC)
  private String channelRemoveTopic;

  @KafkaListener(topics = CHANNEL_SUBSCRIBED_TOPIC, groupId = GROUP_ID)
  public void listenChannelSubscribedEvent(ChannelSubscribedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ChannelStatisticsListener.class.getSimpleName(),
        event,
        channelSubscribedTopic
    );

    service.incrementSubscribers(event);
  }

  @KafkaListener(topics = CHANNEL_UNSUBSCRIBED_TOPIC, groupId = GROUP_ID)
  public void listenChannelUnsubscribedEvent(ChannelUnsubscribedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ChannelStatisticsListener.class.getSimpleName(),
        event,
        channelUnsubscribedTopic
    );

    service.decrementSubscribers(event);
  }

  @KafkaListener(topics = CHANNEL_REMOVED_TOPIC, groupId = GROUP_ID)
  public void listenChannelRemovedEvent(ChannelRemovedEvent event) {

    log.debug(
        "{} consumed {} from topic: \n{}",
        ChannelStatisticsListener.class.getSimpleName(),
        event,
        channelRemoveTopic
    );

    service.removeChannel(event);
  }
}
