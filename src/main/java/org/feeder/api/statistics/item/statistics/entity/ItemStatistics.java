package org.feeder.api.statistics.item.statistics.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "item_statistics")
@EqualsAndHashCode(callSuper = false)
public class ItemStatistics extends BaseEntity<UUID> {

  @Id
  @Column(name = "it_id", updatable = false)
  private UUID itemId;

  @NotNull
  @Column(name = "ch_id", updatable = false)
  private UUID channelId;

  @Column(name = "is_number_of_views")
  private long numberOfViews;

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "is_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "is_modified")
  private LocalDateTime modified;

  @Override
  public UUID getId() {
    return itemId;
  }

  public void incrementNumberOfViews() {
    numberOfViews++;
  }
}
