package com.restaurant.statistics;

import com.restaurant.kitchen.Cook;
import com.restaurant.statistics.event.EventDataRow;
import com.restaurant.statistics.event.EventType;
import com.restaurant.statistics.event.OrderReadyEventDataRow;
import com.restaurant.statistics.event.VideosSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class StatisticsManager {
  private static StatisticsManager instance;
  private StatisticsStorage statisticsStorage = new StatisticsStorage();
  private Set<Cook> cooks = new HashSet<>();

  private StatisticsManager() {
  }

  public static synchronized StatisticsManager getInstance() {
    if (instance == null) {
      instance = new StatisticsManager();
    }
    return instance;
  }

  public Set<Cook> getCooks() {
    return cooks;
  }

  public void register(Cook cook) {
    this.cooks.add(cook);
  }

  public void record(EventDataRow data) {
    this.statisticsStorage.put(data);
  }

  public Map<String, Long> getProfitMap() {
    Map<String, Long> res = new HashMap<>();
    List<EventDataRow> rows = statisticsStorage.get(EventType.VIDEOS_SELECTED);
    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    long total = 0l;
    for (EventDataRow row : rows) {
      VideosSelectedEventDataRow dataRow = (VideosSelectedEventDataRow) row;
      String date = format.format(dataRow.getDate());
      if (!res.containsKey(date)) {
        res.put(date, 0l);
      }
      total += dataRow.getAmount();
      res.put(date, res.get(date) + dataRow.getAmount());
    }
    res.put("Total", total);
    return res;
  }

  public Map<String, Map<String, Integer>> getCookWorkloadingMap() {
    Map<String, Map<String, Integer>> res = new HashMap<>();
    List<EventDataRow> rows = statisticsStorage.get(EventType.ORDER_READY);
    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    for (EventDataRow row : rows) {
      OrderReadyEventDataRow dataRow = (OrderReadyEventDataRow) row;
      String date = format.format(dataRow.getDate());
      if (!res.containsKey(date)) {
        res.put(date, new HashMap<>());
      }
      String cookName = dataRow.getCookName();
      if (!res.get(date).containsKey(cookName)) {
        res.get(date).put(cookName, 0);
      }
      res.get(date).put(cookName, res.get(date).get(cookName) + +dataRow.getTime());
    }
    return res;
  }

  private class StatisticsStorage {
    private final Map<EventType, List<EventDataRow>> storage = new HashMap<>();

    public StatisticsStorage() {
      for (EventType eventType : EventType.values()) {
        storage.put(eventType, new ArrayList<>());
      }
    }

    private void put(EventDataRow data) {
      EventType type = data.getType();
      if (!this.storage.containsKey(type))
        throw new UnsupportedOperationException();

      this.storage.get(type).add(data);
    }

    public List<EventDataRow> get(EventType type) {
      if (!this.storage.containsKey(type))
        throw new UnsupportedOperationException();

      return this.storage.get(type);
    }
  }
}
