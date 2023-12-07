package com.restaurant.kitchen;

import com.restaurant.ConsoleHelper;
import com.restaurant.statistics.StatisticsManager;
import com.restaurant.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
  private String name;

  public Cook(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public void update(Observable o, Object arg) {
    Order order = (Order) arg;
    ConsoleHelper.writeMessage("Start cooking - " + order);
    setChanged();
    notifyObservers(order);
    OrderReadyEventDataRow row = new OrderReadyEventDataRow(order.getTablet().toString(), name,
        order.getTotalCookingTime() * 60, order.getDishes());
    StatisticsManager.getInstance().record(row);
  }
}
