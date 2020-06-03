package org.feeder.api.statistics.itemstatistics.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemViewedEvent {

  private UUID itemId;
}
