package org.feeder.api.statistics.item.statistics.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRemovedEvent {

  private UUID itemId;
}
