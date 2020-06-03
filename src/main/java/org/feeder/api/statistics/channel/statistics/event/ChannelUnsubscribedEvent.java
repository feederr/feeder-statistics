package org.feeder.api.statistics.channel.statistics.event;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelUnsubscribedEvent extends ChannelBaseEvent {

  public ChannelUnsubscribedEvent(UUID channelId) {
    super(channelId);
  }
}
