package org.feeder.api.statistics.channel.statistics.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "channel_statistics")
@EqualsAndHashCode(callSuper = false)
public class ChannelStatistics extends BaseEntity<UUID> {

  @Id
  @Column(name = "ch_id", updatable = false)
  private UUID channelId;

  @Column(name = "cs_number_of_subscribers")
  private long numberOfSubscribers;

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "cs_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "cs_modified")
  private LocalDateTime modified;

  @Override
  public UUID getId() {
    return channelId;
  }

  public void incrementNumberOfSubscribers() {
    numberOfSubscribers++;
  }

  public void decrementNumberOfSubscribers() {
    numberOfSubscribers--;
  }
}
