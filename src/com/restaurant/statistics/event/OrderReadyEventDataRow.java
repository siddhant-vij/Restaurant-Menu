package com.restaurant.statistics.event;

import com.restaurant.kitchen.Dish;

import java.util.Date;
import java.util.List;

public class OrderReadyEventDataRow implements EventDataRow {
  private final String tabletName;
  private final String cookName;
  private final int cookingTimeSeconds;
  private final List<Dish> dishesInOrder;
  private final Date currentDate;

  public OrderReadyEventDataRow(
      String tabletName,
      String cookName,
      int cookingTimeSeconds,
      List<Dish> dishesInOrder) {
    this.tabletName = tabletName;
    this.cookName = cookName;
    this.cookingTimeSeconds = cookingTimeSeconds;
    this.dishesInOrder = dishesInOrder;
    this.currentDate = new Date();
  }

  public String getCookName() {
    return cookName;
  }

  public String getTabletName() {
    return tabletName;
  }

  public List<Dish> getDishesInOrder() {
    return dishesInOrder;
  }

  @Override
  public EventType getType() {
    return EventType.ORDER_READY;
  }

  @Override
  public Date getDate() {
    return currentDate;
  }

  @Override
  public int getTime() {
    return cookingTimeSeconds;
  }
}
