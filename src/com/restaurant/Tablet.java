package com.restaurant;

import com.restaurant.kitchen.Order;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Observable;

public class Tablet extends Observable {
  private final int number;
  private static Logger logger = Logger.getLogger(Tablet.class.getName());

  public Tablet(int number) {
    this.number = number;
  }

  public Order createOrder() {
    Order order = null;
    try {
      order = new Order(this);
      if (order.isEmpty()) {
        return null;
      }
      setChanged();
      notifyObservers(order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "The console is unavailable.");
    }
    return order;
  }

  @Override
  public String toString() {
    return "Tablet{" +
        "number=" + number +
        '}';
  }
}
