package org.feeder.api.statistics.itemstatistics.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.statistics.itemstatistics.ItemStatisticsMapper;
import org.feeder.api.statistics.itemstatistics.ItemStatisticsRepository;
import org.feeder.api.statistics.itemstatistics.entity.ItemStatistics;
import org.feeder.api.statistics.itemstatistics.event.ItemViewedEvent;
import org.feeder.api.statistics.itemstatistics.vo.ItemStatisticsResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemStatisticsService {

  private final ItemStatisticsRepository repository;

  private final ItemStatisticsMapper mapper;

  @Transactional(propagation = Propagation.REQUIRED)
  public void updateStatistics(ItemViewedEvent event) {

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
      entity.setNew(true);
    }

    entity.incrementNumberOfViews();

    repository.save(entity);
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
