package com.restaurant.kitchen;

import com.restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {
  @Override
  public void update(Observable cook, Object order) {
    ConsoleHelper.writeMessage(order + " was prepared by " + cook);
  }
}
