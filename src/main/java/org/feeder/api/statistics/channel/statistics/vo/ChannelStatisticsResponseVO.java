package org.feeder.api.statistics.channel.statistics.vo;

import java.util.UUID;
import lombok.Data;

@Data
public class ChannelStatisticsResponseVO {

  private UUID channelId;

  private Long numberOfSubscribers;
}
