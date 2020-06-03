package org.feeder.api.statistics.item.statistics;

import org.feeder.api.statistics.item.statistics.entity.ItemStatistics;
import org.feeder.api.statistics.item.statistics.vo.ItemStatisticsResponseVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ItemStatisticsMapper {

  private final ModelMapper mapper = new ModelMapper();

  public ItemStatisticsMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  public ItemStatisticsResponseVO toResponseVO(ItemStatistics entity) {
    return mapper.map(entity, ItemStatisticsResponseVO.class);
  }
}
