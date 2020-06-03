package org.feeder.api.statistics.itemstatistics;

import static org.feeder.api.statistics.itemstatistics.ItemStatisticsController.ITEM_STATISTICS_PATH;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.statistics.itemstatistics.service.ItemStatisticsService;
import org.feeder.api.statistics.itemstatistics.vo.ItemStatisticsResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ITEM_STATISTICS_PATH)
@RequiredArgsConstructor
public class ItemStatisticsController {

  protected static final String ITEM_STATISTICS_PATH = "/item-statistics";

  private static final String ID_PATH = "/{id}";

  private final ItemStatisticsService service;

  @GetMapping(ID_PATH)
  public ResponseEntity<ItemStatisticsResponseVO> get(@PathVariable final UUID id) {
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.get(id));
  }
}
