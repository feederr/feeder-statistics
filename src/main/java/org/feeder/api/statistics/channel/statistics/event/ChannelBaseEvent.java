package org.feeder.api.statistics.channel.statistics.event;

import java.util.UUID;
import lombok.Data;

@Data
public class ChannelBaseEvent {

  private UUID channelId;
}
