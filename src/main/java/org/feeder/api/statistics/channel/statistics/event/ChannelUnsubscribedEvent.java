package org.feeder.api.statistics.channel.statistics.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChannelUnsubscribedEvent extends ChannelBaseEvent {

}
