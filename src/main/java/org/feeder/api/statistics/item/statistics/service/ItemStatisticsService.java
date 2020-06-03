package org.feeder.api.statistics.item.statistics.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.statistics.item.statistics.ItemStatisticsMapper;
import org.feeder.api.statistics.item.statistics.ItemStatisticsRepository;
import org.feeder.api.statistics.item.statistics.vo.ItemStatisticsResponseVO;
import org.feeder.api.statistics.item.statistics.entity.ItemStatistics;
import org.feeder.api.statistics.item.statistics.event.ItemRemovedEvent;
import org.feeder.api.statistics.item.statistics.event.ItemViewedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemStatisticsService {

  private final ItemStatisticsRepository repository;

  private final ItemStatisticsMapper mapper;

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public void updateViews(ItemViewedEvent event) {

    Optional<ItemStatistics> entityOpt = repository.findById(event.getItemId());

    ItemStatistics entity;

    if (entityOpt.isPresent()) {

      log.debug("Found {} by itemId: {}", ItemStatistics.class.getSimpleName(), event.getItemId());
      entity = entityOpt.get();

    } else {

      log.debug(
          "{} not found by itemId: {}. Creating new",
          ItemStatistics.class.getSimpleName(),
          event.getItemId()
      );

      entity = new ItemStatistics();
      entity.setItemId(event.getItemId());
      entity.setChannelId(entity.getChannelId());
      entity.setNew(true);
    }

    entity.incrementNumberOfViews();

    repository.save(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public void removeStatistics(ItemRemovedEvent event) {

    Optional<ItemStatistics> entityOpt = repository.findById(event.getItemId());

    ItemStatistics entity;

    if (entityOpt.isPresent()) {

      log.debug("Found {} by itemId: {}", ItemStatistics.class.getSimpleName(), event.getItemId());
      entity = entityOpt.get();

    } else {

      log.debug(
          "{} not found by itemId: {}",
          ItemStatistics.class.getSimpleName(),
          event.getItemId()
      );

      return;
    }

    repository.delete(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public ItemStatisticsResponseVO get(UUID itemId) {

    Optional<ItemStatistics> entityOpt = repository.findById(itemId);

    ItemStatistics entity = entityOpt.orElseThrow(() -> new EntityNotFoundException(
        String.format("%s = %s not found", ItemStatistics.class.getSimpleName(), itemId),
        ItemStatistics.class,
        itemId
    ));

    return mapper.toResponseVO(entity);
  }
}
