package org.feeder.api.statistics.channel.statistics.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.statistics.channel.statistics.ChannelStatisticsMapper;
import org.feeder.api.statistics.channel.statistics.ChannelStatisticsRepository;
import org.feeder.api.statistics.channel.statistics.entity.ChannelStatistics;
import org.feeder.api.statistics.channel.statistics.event.ChannelRemovedEvent;
import org.feeder.api.statistics.channel.statistics.event.ChannelSubscribedEvent;
import org.feeder.api.statistics.channel.statistics.event.ChannelUnsubscribedEvent;
import org.feeder.api.statistics.channel.statistics.vo.ChannelStatisticsResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelStatisticsService {

  private final ChannelStatisticsRepository repository;

  private final ChannelStatisticsMapper mapper;

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public void incrementSubscribers(ChannelSubscribedEvent event) {

    Optional<ChannelStatistics> entityOpt = repository.findById(event.getChannelId());

    ChannelStatistics entity;

    if (entityOpt.isPresent()) {

      log.debug(
          "Found {} by channelId: {}",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId()
      );
      entity = entityOpt.get();

    } else {

      log.debug(
          "{} not found by channelId: {}. Creating new",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId()
      );

      entity = new ChannelStatistics();
      entity.setChannelId(event.getChannelId());
      entity.setNew(true);
    }

    entity.incrementNumberOfSubscribers();

    repository.save(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public void decrementSubscribers(ChannelUnsubscribedEvent event) {

    Optional<ChannelStatistics> entityOpt = repository.findById(event.getChannelId());

    ChannelStatistics entity;

    if (entityOpt.isPresent()) {

      log.debug(
          "Found {} by channelId: {}",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId()
      );
      entity = entityOpt.get();

    } else {

      log.debug(
          "{} not found by channelId: {}. Creating new",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId()
      );

      return;
    }

    entity.decrementNumberOfSubscribers();

    repository.save(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public void removeChannel(ChannelRemovedEvent event) {

    Optional<ChannelStatistics> entityOpt = repository.findById(event.getChannelId());

    ChannelStatistics entity;

    if (entityOpt.isPresent()) {

      log.debug(
          "Found {} by channelId: {}",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId());

      entity = entityOpt.get();

    } else {

      log.debug(
          "{} not found by channelId: {}",
          ChannelStatistics.class.getSimpleName(),
          event.getChannelId()
      );

      return;
    }

    repository.delete(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public ChannelStatisticsResponseVO get(UUID id) {

    Optional<ChannelStatistics> entityOpt = repository.findById(id);

    ChannelStatistics entity = entityOpt.orElseThrow(() -> new EntityNotFoundException(
        String.format("%s = %s not found", ChannelStatistics.class.getSimpleName(), id),
        ChannelStatistics.class,
        id
    ));

    return mapper.toResponseVO(entity);
  }
}
