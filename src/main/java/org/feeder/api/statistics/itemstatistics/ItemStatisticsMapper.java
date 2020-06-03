package org.feeder.api.statistics.itemstatistics;

import org.feeder.api.statistics.itemstatistics.entity.ItemStatistics;
import org.feeder.api.statistics.itemstatistics.vo.ItemStatisticsResponseVO;
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
