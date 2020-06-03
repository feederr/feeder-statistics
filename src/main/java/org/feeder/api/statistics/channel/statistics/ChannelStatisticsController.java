package org.feeder.api.statistics.channel.statistics;

import static org.feeder.api.statistics.channel.statistics.ChannelStatisticsController.CHANNEL_STATISTICS_PATH;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.statistics.channel.statistics.service.ChannelStatisticsService;
import org.feeder.api.statistics.channel.statistics.vo.ChannelStatisticsResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CHANNEL_STATISTICS_PATH)
@RequiredArgsConstructor
public class ChannelStatisticsController {

  protected static final String CHANNEL_STATISTICS_PATH = "/channel-statistics";

  private static final String ID_PATH = "/{id}";

  private final ChannelStatisticsService service;

  @GetMapping(ID_PATH)
  public ResponseEntity<ChannelStatisticsResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }
}
