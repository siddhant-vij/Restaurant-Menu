package com.restaurant.kitchen;

import com.restaurant.ConsoleHelper;
import com.restaurant.statistics.StatisticsManager;
import com.restaurant.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
  private String name;
  private boolean busy;
  private LinkedBlockingQueue<Order> queue;

  public Cook(String name) {
    this.name = name;
  }

  public boolean isBusy() {
    return busy;
  }

  public LinkedBlockingQueue<Order> getQueue() {
    return queue;
  }

  public void setQueue(LinkedBlockingQueue<Order> queue) {
    this.queue = queue;
  }

  @Override
  public String toString() {
    return name;
  }

  public void startCookingOrder(Order order) {
    this.busy = true;
    ConsoleHelper.writeMessage(name + " Start cooking - " + order);
    int totalCookingTime = order.getTotalCookingTime();
    OrderReadyEventDataRow row = new OrderReadyEventDataRow(order.getTablet().toString(), name, totalCookingTime * 60,
        order.getDishes());
    StatisticsManager.getInstance().record(row);
    try {
      Thread.sleep((long) (totalCookingTime * 10));
    } catch (InterruptedException ignored) {
    }
    setChanged();
    notifyObservers(order);
    this.busy = false;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(10);
        if (!queue.isEmpty()) {
          if (!this.isBusy()) {
            this.startCookingOrder(queue.take());
          }
        }
      }
    } catch (InterruptedException e) {
    }
  }
}
