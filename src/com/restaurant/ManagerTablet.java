package com.restaurant;

import com.restaurant.ad.Advertisement;
import com.restaurant.ad.StatisticsAdvertisementManager;
import com.restaurant.statistics.StatisticsManager;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ManagerTablet {
  public void printAdRevenue() {
    StatisticsManager manager = StatisticsManager.getInstance();
    Map<String, Long> adRevenue = manager.getProfitMap();
    adRevenue.entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByKey().reversed())
        .forEach(entry -> {
          if (!entry.getKey().equals("Total")) {
            ConsoleHelper.writeMessage(String.format(
                "%s - %.2f",
                entry.getKey(),
                1.0 * entry.getValue() / 100));
          }
        });
    Long totalAmount = adRevenue.get("Total");
    ConsoleHelper.writeMessage(String.format(
        "Total - %.2f",
        1.0 * totalAmount / 100));
  }

  public void printCookUtilization() {
    StatisticsManager manager = StatisticsManager.getInstance();
    Map<String, Map<String, Integer>> cookWorkload = manager.getCookWorkloadingMap();
    cookWorkload.entrySet().stream()
        .sorted(Map.Entry.<String, Map<String, Integer>>comparingByKey().reversed())
        .forEach(dateEntry -> {
          ConsoleHelper.writeMessage(dateEntry.getKey());
          dateEntry.getValue().entrySet().stream()
              .sorted(Map.Entry.comparingByKey())
              .forEach(cookEntry -> {
                int workedMinutes = (int) Math.ceil(cookEntry.getValue() / 60.0);
                if (workedMinutes > 0) {
                  ConsoleHelper.writeMessage(String.format(
                      "%s - %d min",
                      cookEntry.getKey(),
                      workedMinutes));
                }
              });
        });

  }

  public void printActiveVideoSet() {
    StatisticsAdvertisementManager manager = StatisticsAdvertisementManager.getInstance();
    List<Advertisement> activeVideos = manager.getVideoSet(true);
    activeVideos.stream()
        .sorted(Comparator.comparing(ad -> ad.getName().toLowerCase()))
        .forEach(ad -> ConsoleHelper.writeMessage(String.format(
            "%s - %d",
            ad.getName(),
            ad.getHits())));
  }

  public void printArchivedVideoSet() {
    StatisticsAdvertisementManager manager = StatisticsAdvertisementManager.getInstance();
    List<Advertisement> archivedVideos = manager.getVideoSet(false);
    archivedVideos.stream()
        .sorted(Comparator.comparing(ad -> ad.getName().toLowerCase()))
        .forEach(ad -> ConsoleHelper.writeMessage(ad.getName()));
  }
}
