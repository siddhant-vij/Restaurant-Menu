package com.restaurant;

import com.restaurant.ad.AdvertisementManager;
import com.restaurant.ad.NoVideoAvailableException;
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
      AdvertisementManager advertisementManager = new AdvertisementManager(
          order.getTotalCookingTime() * 60);
      advertisementManager.processVideos();
      setChanged();
      notifyObservers(order);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "The console is unavailable.");
    } catch (NoVideoAvailableException e) {
      logger.log(Level.INFO, "No video is available for the following order: " + order);
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
