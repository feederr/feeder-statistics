package org.feeder.api.statistics;

import org.feeder.api.core.annotation.FeederService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@FeederService
public class StatisticsApplication {

  public static void main(String[] args) {
    SpringApplication.run(StatisticsApplication.class, args);
  }
}
