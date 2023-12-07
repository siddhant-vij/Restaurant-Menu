package com.restaurant.statistics.event;

import java.util.Date;

public class NoVideosAvailableEventDataRow implements EventDataRow {
  private final int totalDuration;
  private final Date currentDate;

  public NoVideosAvailableEventDataRow(int totalDuration) {
    this.totalDuration = totalDuration;
    this.currentDate = new Date();
  }

  @Override
  public EventType getType() {
    return EventType.NO_VIDEOS_AVAILABLE;
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
