package com.restaurant;

import com.restaurant.kitchen.Cook;
import com.restaurant.kitchen.Waiter;

public class Restaurant {
  public static void main(String[] args) {
    Tablet tablet = new Tablet(5);
    Cook cook = new Cook("Amigo");
    Waiter waiter = new Waiter();
    tablet.addObserver(cook);
    cook.addObserver(waiter);

    tablet.createOrder();
    tablet.createOrder();
    tablet.createOrder();
    tablet.createOrder();

    ManagerTablet managerTablet = new ManagerTablet();
    managerTablet.printAdRevenue();
    managerTablet.printCookUtilization();
    managerTablet.printActiveVideoSet();
    managerTablet.printArchivedVideoSet();
  }
}
