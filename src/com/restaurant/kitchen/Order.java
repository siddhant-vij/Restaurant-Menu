package com.restaurant.kitchen;

import com.restaurant.ConsoleHelper;
import com.restaurant.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
  private final Tablet tablet;
  protected List<Dish> dishes;

  public Order(Tablet tablet) throws IOException {
    this.tablet = tablet;
    initDishes();
    ConsoleHelper.writeMessage(toString());
  }

  protected void initDishes() throws IOException {
    this.dishes = ConsoleHelper.getAllDishesForOrder();
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    if (dishes.size() == 0)
      return result.toString();
    result.append("Your order: [" + dishes.get(0));
    for (int i = 1; i < dishes.size(); i++) {
      result.append(", " + dishes.get(i).name());
    }
    result.append("] from " + tablet);
    result.append(", cooking time " + getTotalCookingTime() + " min");
    return result.toString();
  }

  public Tablet getTablet() {
    return tablet;
  }

  public List<Dish> getDishes() {
    return dishes;
  }

  public boolean isEmpty() {
    return dishes.isEmpty();
  }

  public int getTotalCookingTime() {
    int cookingTime = 0;
    for (Dish dish : dishes) {
      cookingTime += dish.getDuration();
    }
    return cookingTime;
  }
}
