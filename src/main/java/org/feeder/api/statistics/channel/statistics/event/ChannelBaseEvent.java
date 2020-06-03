package org.feeder.api.statistics.channel.statistics.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChannelBaseEvent {

  private UUID channelId;
}
