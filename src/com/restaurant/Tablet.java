package com.restaurant;

import com.restaurant.ad.AdvertisementManager;
import com.restaurant.ad.NoVideoAvailableException;
import com.restaurant.kitchen.Order;
import com.restaurant.kitchen.TestOrder;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.concurrent.LinkedBlockingQueue;

public class Tablet {
  private final int number;
  private static Logger logger = Logger.getLogger(Tablet.class.getName());
  private LinkedBlockingQueue<Order> queue;

  public Tablet(int number) {
    this.number = number;
  }

  public LinkedBlockingQueue<Order> getQueue() {
    return queue;
  }

  public void setQueue(LinkedBlockingQueue<Order> queue) {
    this.queue = queue;
  }

  public void createOrder() {
    Order order = null;
    try {
      order = new Order(this);
      processOrder(order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "The console is unavailable.");
    } catch (NoVideoAvailableException e) {
      logger.log(Level.INFO, "No video is available for the following order: " + order);
    }
  }

  public void createTestOrder() {
    Order order = null;
    try {
      order = new TestOrder(this);
      processOrder(order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "The console is unavailable.");
    } catch (NoVideoAvailableException e) {
      logger.log(Level.INFO, "No video is available for the following order: " + order);
    }
  }

  private boolean processOrder(Order order) {
    ConsoleHelper.writeMessage(order.toString());
    if (order.isEmpty())
      return true;
    queue.offer(order);
    new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
    return false;
  }

  @Override
  public String toString() {
    return "Tablet{" +
        "number=" + number +
        '}';
  }
}
