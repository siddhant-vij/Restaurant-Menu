package com.restaurant;

import com.restaurant.kitchen.Cook;
import com.restaurant.kitchen.Order;
import com.restaurant.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
  private static final int ORDER_CREATION_INTERVAL = 100;
  private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(200);

  public static void main(String[] args) {
    List<Tablet> tablets = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Tablet tablet = new Tablet(i + 1);
      tablet.setQueue(orderQueue);
      tablets.add(tablet);
    }

    Cook cook1 = new Cook("Amigo");
    cook1.setQueue(orderQueue);
    Cook cook2 = new Cook("FIFA");
    cook2.setQueue(orderQueue);

    Waiter waiter = new Waiter();
    cook1.addObserver(waiter);
    cook2.addObserver(waiter);

    Thread cook11 = new Thread(cook1);
    cook11.start();
    Thread cook22 = new Thread(cook2);
    cook22.start();
    Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATION_INTERVAL));
    thread.start();

    try {
      Thread.sleep(1000);
      thread.interrupt();
      thread.join();
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

    ManagerTablet managerTablet = new ManagerTablet();
    managerTablet.printAdRevenue();
    managerTablet.printCookUtilization();
    managerTablet.printActiveVideoSet();
    managerTablet.printArchivedVideoSet();
  }
}
