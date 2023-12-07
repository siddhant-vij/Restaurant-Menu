package com.restaurant.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticsAdvertisementManager {
  private static StatisticsAdvertisementManager instance;
  private AdvertisementStorage adStorage = AdvertisementStorage.getInstance();

  private StatisticsAdvertisementManager() {
  }

  public static synchronized StatisticsAdvertisementManager getInstance() {
    if (instance == null) {
      instance = new StatisticsAdvertisementManager();
    }
    return instance;
  }

  public List<Advertisement> getVideoSet(boolean isActive) {
    return adStorage.list().stream()
        .filter(ad -> !isActive ^ ad.isActive())
        .collect(Collectors.toList());
  }
}
