package com.restaurant.statistics.event;

import com.restaurant.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideosSelectedEventDataRow implements EventDataRow {
  private final List<Advertisement> optimalVideoSet;
  private final long amount;
  private final int totalDuration;
  private final Date currentDate;

  public VideosSelectedEventDataRow(
      List<Advertisement> optimalVideoSet,
      long amount,
      int totalDuration) {
    this.optimalVideoSet = optimalVideoSet;
    this.amount = amount;
    this.totalDuration = totalDuration;
    this.currentDate = new Date();
  }

  public List<Advertisement> getOptimalVideoSet() {
    return optimalVideoSet;
  }

  public long getAmount() {
    return amount;
  }

  @Override
  public EventType getType() {
    return EventType.VIDEOS_SELECTED;
  }

  @Override
  public Date getDate() {
    return currentDate;
  }

  @Override
  public int getTime() {
    return totalDuration;
  }
}
