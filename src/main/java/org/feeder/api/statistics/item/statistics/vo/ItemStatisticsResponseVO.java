package org.feeder.api.statistics.item.statistics.vo;

import java.util.UUID;
import lombok.Data;

@Data
public class ItemStatisticsResponseVO {

  private UUID itemId;

  private Long numberOfViews;
}
